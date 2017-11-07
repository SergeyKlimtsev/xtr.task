package xtr.task.fetch;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import xtr.task.json.VacanciesHolder;
import xtr.task.mappers.toEntity.VacancyToEntity;
import xtr.task.properties.PropertiesProvider;
import xtr.task.service.VacancyService;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by root on 05.11.2017.
 */
@Component
public class FetchImpl implements Fetch {

    @Autowired
    private PropertiesProvider properties;

    @Autowired
    private VacancyService vacancyService;

    @Autowired
    private VacancyToEntity vacancyMapper;

    @Autowired
    private RestTemplate restTemplate;

    // Каждые 3 часа будет выполняться загрузка вакансий
    @Scheduled(fixedDelay = 10800000)
    @Override
    public void fetchVacancies() {
        val vacanciesHolder = restTemplate.getForObject(createUrl(), VacanciesHolder.class);
        vacancyService.addAll(vacancyMapper.transform(vacanciesHolder.getItems()));
    }


    private String createUrl() {
        return UriComponentsBuilder.fromHttpUrl(properties.getUrl())
                .queryParam("text", String.format("%s %s", properties.getKeyWords(), properties.getCity()))
                .queryParam("per_page", 100)
                .build()
                .toString();

    }
}
