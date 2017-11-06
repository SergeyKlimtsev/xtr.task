package xtr.task.dto;

import lombok.Builder;
import lombok.Value;

/**
 * Created by root on 05.11.2017.
 */
@Value
@Builder
public class VacancyDTO {

    private String name;
    private String salaryFrom;
    private String salaryTo;
    private String currency;
    private String url;
    private EmployerDTO employer;
}
