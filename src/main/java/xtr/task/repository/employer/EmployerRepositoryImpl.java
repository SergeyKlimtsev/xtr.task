package xtr.task.repository.employer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import xtr.task.model.Employer;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by root on 05.11.2017.
 */
@Repository
public class EmployerRepositoryImpl implements EmployerRepository {

    @Autowired
    private EmployerDAO dao;

    @Override
    public Employer save(Employer employer) {
        return dao.save(employer);
    }

    @Override
    public List<Employer> saveAll(List<Employer> employers) {
        return dao.saveAll(employers);
    }

    @Override
    public boolean delete(int id) {
        return dao.delete(id) != 0;
    }

    @Nullable
    @Override
    public Employer get(int id) {
        return dao.getOne(id);
    }

    @Override
    public List<Employer> getAll() {
        return dao.findAll();
    }

    @Override
    public Page<Employer> getPage(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize);
        return dao.findAll(pageRequest);
    }
}
