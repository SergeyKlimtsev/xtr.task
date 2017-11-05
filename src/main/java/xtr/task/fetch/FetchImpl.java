package xtr.task.fetch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import xtr.task.json.VacanciesHolder;
import xtr.task.json.VacancyJson;
import xtr.task.mappers.VacancyToEntity;
import xtr.task.model.Vacancy;
import xtr.task.properties.PropertiesProvider;
import xtr.task.service.VacancyService;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by root on 05.11.2017.
 */
public class FetchImpl implements Fetch {

    @Autowired
    private PropertiesProvider properties;

    @Autowired
    private VacancyService vacancyService;

    @Autowired
    private VacancyToEntity vacancyMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void fetchVacancies() {
        final VacanciesHolder vacanciesHolder = restTemplate.getForObject(properties.getUrl(), VacanciesHolder.class, getQueryParams());
        vacancyService.addAll(vacancyMapper.transform(vacanciesHolder.getItems()));
    }

    private Map<String, String> getQueryParams() {
        final Map<String, String> params = new HashMap<>();
        params.put("area", properties.getCity());
        params.put("text ", properties.getKeyWords());
        return params;
    }
}
