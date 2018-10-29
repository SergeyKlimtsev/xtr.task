package xtr.task.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import xtr.task.model.Employer;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class VacancyJson {
	Integer id;
	String name;
	Salary salary;
	String url;
	Address address;
	EmployerJson employer;
}
