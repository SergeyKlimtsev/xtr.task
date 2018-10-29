package xtr.task.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.FieldDefaults;


@Value
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
