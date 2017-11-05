package xtr.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import xtr.task.exception.NotFoundException;
import xtr.task.model.Employer;
import xtr.task.repository.employer.EmployerRepository;

import javax.annotation.Nonnull;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by root on 05.11.2017.
 */
@Service
public class EmployerServiceImpl implements EmployerService {

    @Autowired
    private EmployerRepository repository;

    @CacheEvict(value = "employers", allEntries = true)
    @Override
    public Employer add(Employer employer) {
        checkNotNull(employer);
        return repository.save(employer);
    }

    @CacheEvict(value = "employers", allEntries = true)
    @Override
    public List<Employer> addAll(List<Employer> employers) {
        return repository.saveAll(employers);
    }

    @CacheEvict(value = "employers", allEntries = true)
    @Override
    public void update(Employer employer) throws NotFoundException {
        checkNotNull(employer);
        checkNotNull(employer.getId(), "Id of employer should be not null");
        repository.save(employer);
    }

    @CacheEvict(value = "employers", allEntries = true)
    @Override
    public void delete(int id) throws NotFoundException {
        if (!repository.delete(id)) {
            throw new NotFoundException(String.format("Employer for id=n% doesn't found", id));
        }
    }

    @Cacheable("employers")
    @Nonnull
    @Override
    public Employer get(int id) throws NotFoundException {
        final Employer employer = repository.get(id);
        if (employer == null) {
            throw new NotFoundException(String.format("Employer for id=n% doesn't found", id));
        }
        return employer;
    }

    @Cacheable("employers")
    @Override
    public List<Employer> getAll() {
        return repository.getAll();
    }

    @Cacheable("employers")
    @Override
    public Page<Employer> getPage(Integer pageNumber, Integer pageSize) {
        return repository.getPage(pageNumber, pageSize);
    }
}
