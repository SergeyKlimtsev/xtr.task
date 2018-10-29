package xtr.task.mappers;

import java.util.List;
import java.util.Optional;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import lombok.val;

import xtr.task.dto.VacancyDto;
import xtr.task.json.VacancyJson;
import xtr.task.model.Employer;
import xtr.task.model.Vacancy;

@Mapper(uses = EmployerMapper.class)
public abstract class VacancyMapper {
	public abstract VacancyDto toDto(Vacancy vacancy);

	public abstract List<VacancyDto> toDto(Iterable<Vacancy> vacancies);

	public abstract Vacancy toEntity(VacancyDto vacancy);

	public abstract List<Vacancy> toEntity(Iterable<VacancyDto> vacancies);

	@Mappings({
			@Mapping(target = "salaryFrom", source = "vacancy.salary.from"),
			@Mapping(target = "salaryTo", source = "vacancy.salary.to"),
			@Mapping(target = "currency", source = "vacancy.salary.currency")
	})
	public abstract VacancyDto toEntity(VacancyJson vacancy);

	public abstract List<VacancyDto> toEntityFromJson(Iterable<VacancyJson> vacancy);

	@AfterMapping
	protected void afterToEntity(final VacancyJson vacancyJson, @MappingTarget final Vacancy vacancy) {
		val employer = Optional.ofNullable(vacancy.getEmployer()).orElseGet(Employer::new);

		Optional.ofNullable(vacancyJson.getAddress())
				.ifPresent(addr -> employer
						.setBuilding(addr.getBuilding())
						.setCity(addr.getCity())
						.setStreet(addr.getStreet()));

		vacancy.setEmployer(employer);
	}
}
