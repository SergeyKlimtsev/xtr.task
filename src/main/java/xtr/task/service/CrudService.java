package xtr.task.service;

import org.springframework.data.domain.Page;
import xtr.task.exception.NotFoundException;

import javax.annotation.Nonnull;
import java.util.List;

import lombok.SneakyThrows;


public interface CrudService<T> {
    T add(T entity);

    List<T> addAll(List<T> entity);

    @SneakyThrows(NotFoundException.class)
    void update(T entity);

    @SneakyThrows(NotFoundException.class)
    void delete(int id);

    @SneakyThrows(NotFoundException.class)
    @Nonnull
    T get(int id);

    List<T> getAll();

    Page<T> getPage(Integer pageNumber, Integer pageSize);
}
