package xtr.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import xtr.task.exception.NotFoundException;
import xtr.task.model.Vacancy;
import xtr.task.repository.vacancy.VacancyRepository;

import javax.annotation.Nonnull;
import java.util.List;

import static com.google.common.base.Preconditions.*;

/**
 * Created by root on 05.11.2017.
 */
@Service
public class VacancyServiceImpl implements VacancyService {

    @Autowired
    private VacancyRepository repository;

    @CacheEvict(value = "employers", allEntries = true)
    @Override
    public Vacancy add(Vacancy vacancy) {
        checkNotNull(vacancy);
        return repository.save(vacancy);
    }

    @Override
    public List<Vacancy> addAll(List<Vacancy> vacancies) {
        return repository.saveAll(vacancies);
    }

    @CacheEvict(value = "employers", allEntries = true)
    @Override
    public void update(Vacancy vacancy) throws NotFoundException {
        checkNotNull(vacancy);
        checkNotNull(vacancy.getId(), "Id of vacancy should be not null");
        repository.save(vacancy);
    }

    @CacheEvict(value = "employers", allEntries = true)
    @Override
    public void delete(int id) throws NotFoundException {
        if (!repository.delete(id)) {
            throw new NotFoundException(String.format("Vacancy for id=n% doesn't found", id));
        }
    }

    @Cacheable("vacancies")
    @Nonnull
    @Override
    public Vacancy get(int id) throws NotFoundException {
        final Vacancy vacancy = repository.get(id);
        if (vacancy == null) {
            throw new NotFoundException(String.format("Vacancy for id=n% doesn't found", id));
        }
        return vacancy;
    }

    @Cacheable("vacancies")
    @Override
    public List<Vacancy> getAll() {
        return repository.getAll();
    }

    @Cacheable("vacancies")
    @Override
    public Page<Vacancy> getPage(Integer pageNumber, Integer pageSize) {
        return repository.getPage(pageNumber, pageSize);
    }
}
