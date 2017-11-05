package xtr.task.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import xtr.task.model.Employer;

/**
 * Created by root on 01.11.2017.
 */

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class VacancyJson {
    private String id;
    private String name;
    private Salary salary;
    private String url;
    private Address address;
    private EmployerJson employer;
}
