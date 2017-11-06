package xtr.task.mappers;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by root on 05.11.2017.
 */
public interface Mapper<I, O> {

    O transform(I object);

    @Nullable
    default List<O> transform(@Nullable List<I> vacancies) {
        if (vacancies == null) {
            return null;
        }
        return vacancies
                .stream()
                .map(this::transform)
                .collect(Collectors.toList());
    }

}
