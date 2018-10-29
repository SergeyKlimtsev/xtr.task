package xtr.task.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import xtr.task.model.Employer;


public interface EmployerRepository extends PagingAndSortingRepository<Employer, Integer> {
}
