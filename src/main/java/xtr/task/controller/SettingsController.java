package xtr.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import xtr.task.dto.SettingsDTO;
import xtr.task.fetch.Fetch;
import xtr.task.properties.PropertiesProvider;

import static org.springframework.util.StringUtils.*;

/**
 * Created by root on 05.11.2017.
 */
@RestController
@RequestMapping(value = SettingsController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class SettingsController {
    static final String REST_URL = "/rest/settings";

    @Autowired
    private PropertiesProvider propertiesProvider;

    @Autowired
    private Fetch fetch;

    @GetMapping
    public SettingsDTO getSettings() {
        return SettingsDTO.builder()
                .url(propertiesProvider.getUrl())
                .city(propertiesProvider.getCity())
                .keyWords(propertiesProvider.getKeyWords())
                .build();
    }

    @PostMapping
    public void setSettings(@RequestBody SettingsDTO settings) {
        if (!isEmpty(settings.getCity())) propertiesProvider.setCity(settings.getCity());

        if (!isEmpty(settings.getKeyWords())) propertiesProvider.setKeyWords(settings.getKeyWords());

        if (!isEmpty(settings.getUrl())) propertiesProvider.setUrl(settings.getUrl());

        fetch.fetchVacancies();
    }
}
