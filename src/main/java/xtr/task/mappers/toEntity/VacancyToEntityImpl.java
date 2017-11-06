package xtr.task.mappers.toEntity;

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
        final Address address = Optional.ofNullable(vacancy.getAddress()).orElse(new Address());
        final Salary salary = Optional.ofNullable(vacancy.getSalary()).orElse(new Salary());
        final Employer employer = Optional.ofNullable(employerMapper.transform(vacancy.getEmployer())).orElse(new Employer());

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
