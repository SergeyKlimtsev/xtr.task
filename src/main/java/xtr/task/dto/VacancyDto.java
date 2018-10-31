package xtr.task.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import lombok.experimental.FieldDefaults;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VacancyDto {
	Integer id;
	String name;
	String salaryFrom;
	String salaryTo;
	String currency;
	String url;
	EmployerDto employer;
}
