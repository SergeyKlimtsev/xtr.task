package xtr.task.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.FieldDefaults;


@Value
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployerDto {
	Integer id;
	String name;
}
