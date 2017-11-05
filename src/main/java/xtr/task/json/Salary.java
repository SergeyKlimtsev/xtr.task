package xtr.task.json;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by root on 01.11.2017.
 */
@Data
@NoArgsConstructor
public class Salary {
    private String to;
    private String gross;
    private String from;
    private String currency;
}
