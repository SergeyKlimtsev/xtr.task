package xtr.task.repository.vacancy;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import xtr.task.model.Vacancy;

import java.util.List;
import java.util.Optional;

/**
 * Created by root on 02.11.2017.
 */
public interface VacancyDAO extends JpaRepository<Vacancy, Integer> {
    @Override
    @Transactional
    Vacancy save(Vacancy user);

    @Override
    Vacancy getOne(Integer integer);

    @Override
    @Query("SELECT v FROM Vacancy v")
    List<Vacancy> findAll();

    @Transactional
    @Modifying
    @Query("DELETE FROM Vacancy v WHERE v.id=:id")
    int delete(@Param("id") int id);
}
