package xtr.task.service;

import java.util.List;

import javax.annotation.Nonnull;

import org.springframework.data.domain.Page;


public interface CrudService<T> {
    T add(T entity);

    List<T> addAll(List<T> entity);

    void update(T entity);

    void delete(int id);

    @Nonnull
    T get(int id);

    List<T> getAll();

    Page<T> getPage(Integer pageNumber, Integer pageSize);
}
