package xtr.task.json;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VacanciesHolder {
     List<VacancyJson> items;
}
