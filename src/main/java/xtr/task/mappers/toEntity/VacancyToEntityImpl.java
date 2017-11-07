package xtr.task.mappers.toEntity;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xtr.task.json.Address;
import xtr.task.json.Salary;
import xtr.task.json.VacancyJson;
import xtr.task.model.Employer;
import xtr.task.model.Vacancy;

import java.util.Optional;

/**
 * Created by root on 05.11.2017.
 */
@Component
public class VacancyToEntityImpl implements VacancyToEntity {

    @Autowired
    private EmployerToEntity employerMapper;

    @Override
    public Vacancy transform(VacancyJson vacancy) {
        val address = Optional.ofNullable(vacancy.getAddress()).orElse(new Address());
        val salary = Optional.ofNullable(vacancy.getSalary()).orElse(new Salary());
        val employer = Optional.ofNullable(employerMapper.transform(vacancy.getEmployer())).orElse(new Employer());

        employer.setBuilding(address.getBuilding());
        employer.setCity(address.getCity());
        employer.setStreet(address.getStreet());

        return Vacancy.builder()
                .id(Integer.parseInt(vacancy.getId()))
                .name(vacancy.getName())
                .employer(employer)
                .currency(salary.getCurrency())
                .salaryFrom(salary.getFrom())
                .salaryTo(salary.getTo())
                .url(vacancy.getUrl())
                .build();
    }
}
