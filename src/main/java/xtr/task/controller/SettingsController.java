package xtr.task.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import xtr.task.config.SwaggerConf;
import xtr.task.dto.SettingsDto;
import xtr.task.fetch.Fetch;
import xtr.task.properties.PropertiesProvider;

import static org.springframework.util.StringUtils.isEmpty;

@Api(tags = SwaggerConf.SETTINGS)
@RequiredArgsConstructor
@RestController
@RequestMapping(value = SettingsController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class SettingsController {
    static final String REST_URL = "/rest/settings";

    private final PropertiesProvider propertiesProvider;
    private final Fetch fetch;

    @ApiOperation(nickname = "getSettings", value = "${settingsController.get}")
    @GetMapping
    public SettingsDto getSettings() {
        return SettingsDto.builder()
                .url(propertiesProvider.getUrl())
                .city(propertiesProvider.getCity())
                .keyWords(propertiesProvider.getKeyWords())
                .build();
    }

    @ApiOperation(nickname = "setSettings", value = "${settingsController.set}")
    @PostMapping
    public void setSettings(@RequestBody SettingsDto settings) {
        if (!isEmpty(settings.getCity())) propertiesProvider.setCity(settings.getCity());

        if (!isEmpty(settings.getKeyWords())) propertiesProvider.setKeyWords(settings.getKeyWords());

        if (!isEmpty(settings.getUrl())) propertiesProvider.setUrl(settings.getUrl());

        fetch.fetchVacancies();
    }
}
