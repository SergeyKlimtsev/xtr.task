package xtr.task.mappers.toDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xtr.task.dto.VacancyDTO;
import xtr.task.model.Vacancy;

/**
 * Created by root on 05.11.2017.
 */
@Component
public class VacancyToDtoImpl implements VacancyToDto {

    @Autowired
    private EmployerToDto employerToDto;

    @Override
    public VacancyDTO transform(Vacancy vacancy) {
        return VacancyDTO.builder()
                .name(vacancy.getName())
                .url(vacancy.getUrl())
                .salaryFrom(vacancy.getSalaryFrom())
                .salaryTo(vacancy.getSalaryTo())
                .currency(vacancy.getCurrency())
                .employer(employerToDto.transform(vacancy.getEmployer()))
                .build();
    }
}
