package xtr.task.controller;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import xtr.task.dto.VacancyDTO;
import xtr.task.mappers.toDto.VacancyToDto;
import xtr.task.model.Vacancy;
import xtr.task.service.VacancyService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by root on 05.11.2017.
 */
@RestController
@RequestMapping(value = VacancyController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VacancyController {
    static final String REST_URL = "/rest/vacancies";

    @Autowired
    private VacancyService vacancyService;

    @Autowired
    private VacancyToDto vacancyToDto;

    @GetMapping("/pages")
    public Page<VacancyDTO> getPage(@RequestParam("pageNumber") Integer pageNumber,
                                    @RequestParam("pageSize") Integer pageSize) {
        val page = vacancyService.<Vacancy>getPage(pageNumber, pageSize);
        return page.map(item -> vacancyToDto.transform(item));
    }

    @GetMapping
    public List<VacancyDTO> getAll() {
        val allVacancies = vacancyService.getAll();
        return allVacancies.stream().map(item -> vacancyToDto.transform(item)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public VacancyDTO getOne(@PathVariable("id") int id) {
        val vacancy = vacancyService.get(id);
        return vacancyToDto.transform(vacancy);
    }

}

