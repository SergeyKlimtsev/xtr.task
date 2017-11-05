package xtr.task.mappers;

import org.springframework.stereotype.Component;
import xtr.task.json.EmployerJson;
import xtr.task.model.Employer;

/**
 * Created by root on 05.11.2017.
 */
@Component
public class EmployerToEntityImpl implements EmployerToEntity {

    @Override
    public Employer transform(EmployerJson object) {
        if (object == null) {
            return null;
        }
        return Employer.builder()
                .id(Integer.parseInt(object.getId()))
                .name(object.getName())
                .build();
    }
}
