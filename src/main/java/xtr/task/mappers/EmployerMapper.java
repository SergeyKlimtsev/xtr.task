package xtr.task.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import xtr.task.dto.EmployerDto;
import xtr.task.json.EmployerJson;
import xtr.task.model.Employer;

@Mapper
public interface EmployerMapper {
	EmployerDto toDto(Employer employer);

	List<EmployerDto> toDto(Iterable<Employer> employer);

	Employer toEntity(EmployerDto employer);

	List<Employer> toEntity(Iterable<EmployerDto> employer);

	Employer toEntity(EmployerJson employer);
}
