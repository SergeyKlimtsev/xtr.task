package xtr.task.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import xtr.task.dto.VacancyDto;
import xtr.task.service.VacancyService;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = VacancyController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VacancyController {
	static final String REST_URL = "/rest/vacancies";

	private final VacancyService vacancyService;

	@GetMapping("/pages")
	public Page<VacancyDto> getPage(@RequestParam("pageNumber") Integer pageNumber,
			@RequestParam("pageSize") Integer pageSize) {
		log.trace("Vacancy page request pageNumber: {}, pageSize: {}", pageNumber, pageSize);
		return vacancyService.getPage(pageNumber, pageSize);
	}

	@GetMapping
	public List<VacancyDto> getAll() {
		log.trace("Vacancy get all request");
		return vacancyService.getAll();
	}

	@GetMapping("/{id}")
	public VacancyDto getOne(@PathVariable("id") int id) {
		log.trace("Vacancy get one request: {}", id);
		return vacancyService.get(id);
	}

}

