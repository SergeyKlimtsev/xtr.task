package xtr.task.service;

import org.springframework.data.domain.Page;
import xtr.task.exception.NotFoundException;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * Created by root on 05.11.2017.
 */
public interface CrudService<T> {
    T add(T entity);

    List<T> addAll(List<T> entity);

    void update(T entity) throws NotFoundException;

    void delete(int id) throws NotFoundException;

    @Nonnull
    T get(int id) throws NotFoundException;

    List<T> getAll();

    Page<T> getPage(Integer pageNumber, Integer pageSize);
}
