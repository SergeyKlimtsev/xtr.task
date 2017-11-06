package xtr.task.mappers.toDto;

import org.springframework.stereotype.Component;
import xtr.task.dto.EmployerDTO;
import xtr.task.model.Employer;

/**
 * Created by root on 05.11.2017.
 */
@Component
public class EmployerToDtoImpl implements EmployerToDto {
    @Override
    public EmployerDTO transform(Employer employer) {
        return EmployerDTO.builder()
                .name(employer.getName())
                .build();
    }
}
