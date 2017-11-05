package xtr.task.json;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by root on 05.11.2017.
 */
@Data
@NoArgsConstructor
public class VacanciesHolder {
    private List<VacancyJson> items;
}
