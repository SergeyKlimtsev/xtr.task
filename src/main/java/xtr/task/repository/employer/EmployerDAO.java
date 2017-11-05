package xtr.task.repository.employer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import xtr.task.model.Employer;

import java.util.List;

/**
 * Created by root on 02.11.2017.
 */
@Transactional(readOnly = true)
public interface EmployerDAO extends JpaRepository<Employer, Integer> {

    @Override
    @Transactional
    Employer save(Employer user);

    @Override
    Employer findOne(Integer integer);

    @Override
    @Query("SELECT e FROM Employer e")
    List<Employer> findAll();

    @Transactional
    @Modifying
    @Query("DELETE FROM Employer e WHERE e.id=:id")
    int delete(@Param("id") int id);
}
