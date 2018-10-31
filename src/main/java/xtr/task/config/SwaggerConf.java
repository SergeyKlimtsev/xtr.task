package xtr.task.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@PropertySource("classpath:swagger.properties")
public class SwaggerConf {
	public static final String VACANCIES = "Vacancies";
	public static final String SETTINGS = "Settings";
	private static final String TITLE = "Vacancies aggregator REST API";
	private static final String DESCRIPTION = "Service provide possibility for aggregate vacancies by key words";
	private static final String VERSION = "0.1.0";
	private static final String TERMS_OF_SERVICE_URL = null;
	private static final Contact CONTACT = new Contact("Sergey Klimtsev", "https://github.com/SergeyKlimtsev", "sergey.klimtsev@gmail.com");
	private static final String LICENSE = null;
	private static final String LICENSE_URL = null;
	private static final String VACANCIES_DESC = "Vacancies endpoint";
	private static final String SETTINGS_DESC = "Adjust settings";

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("xtr.task.controller"))
				.paths(PathSelectors.any()).build()
				.tags(
						new Tag(VACANCIES, VACANCIES_DESC),
						new Tag(SETTINGS, SETTINGS_DESC))
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfo(TITLE, DESCRIPTION, VERSION, TERMS_OF_SERVICE_URL, CONTACT, LICENSE, LICENSE_URL, new ArrayList<>());
	}
}
