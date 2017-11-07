package xtr.task.repository.vacancy;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import xtr.task.model.Vacancy;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by root on 05.11.2017.
 */
@Repository
public class VacancyRepositoryImpl implements VacancyRepository {

    @Autowired
    private VacancyDAO dao;

    @Override
    public Vacancy save(Vacancy vacancy) {
        return dao.save(vacancy);
    }

    @Override
    public List<Vacancy> saveAll(List<Vacancy> vacancies) {
        return dao.saveAll(vacancies);
    }

    @Override
    public boolean delete(int id) {
        return dao.delete(id) != 0;
    }

    @Nullable
    @Override
    public Vacancy get(int id) {
        return dao.getOne(id);
    }

    @Override
    public List<Vacancy> getAll() {
        return dao.findAll();
    }

    @Override
    public Page<Vacancy> getPage(Integer pageNumber, Integer pageSize) {
        val pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        return dao.findAll(pageRequest);
    }
}
