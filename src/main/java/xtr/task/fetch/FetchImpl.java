package xtr.task.fetch;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;

import xtr.task.json.VacanciesHolder;
import xtr.task.mappers.VacancyMapper;
import xtr.task.properties.PropertiesProvider;
import xtr.task.service.VacancyService;


@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Component
public class FetchImpl implements Fetch {

	PropertiesProvider properties;
	VacancyService vacancyService;
	VacancyMapper vacancyMapper;
	RestTemplate restTemplate;

	// Fixed delay on every 3 hours
	@Scheduled(fixedDelay = 10800000)
	@PostConstruct
	@Override
	public void fetchVacancies() {
		val vacanciesHolder = restTemplate.getForObject(createUrl(), VacanciesHolder.class);
		Optional.ofNullable(vacanciesHolder)
				.ifPresent(holder -> vacancyService.addAll(vacancyMapper.toEntityFromJson(holder.getItems())));
	}


	private String createUrl() {
		return UriComponentsBuilder.fromHttpUrl(properties.getUrl())
				.queryParam("text", String.format("%s %s", properties.getKeyWords(), properties.getCity()))
				.queryParam("per_page", 100)
				.build()
				.toString();

	}
}
