package xtr.task.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import xtr.task.model.Vacancy;

public interface VacancyRepository extends PagingAndSortingRepository<Vacancy, Integer> {
}
