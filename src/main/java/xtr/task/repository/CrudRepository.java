package xtr.task.repository;

import org.springframework.data.domain.Page;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by root on 05.11.2017.
 */
public interface CrudRepository<T> {

    T save(T entity);

    List<T> saveAll(List<T> entity);

    // false if entity cannot be deleted
    boolean delete(int id);

    // null if entity does not exist
    @Nullable
    T get(int id);

    List<T> getAll();

    Page<T> getPage(Integer pageNumber, Integer pageSize);
}
