package xtr.task.service;

import java.util.List;

import xtr.task.dto.VacancyDto;
import xtr.task.model.Vacancy;


public interface VacancyService extends CrudService<VacancyDto> {
	List<VacancyDto> addAll(Iterable<Vacancy> vacancies);
}
