package xtr.task.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by root on 05.11.2017.
 */
@Data
@Builder
@AllArgsConstructor
public class SettingsDTO {

    private String url;
    private String keyWords;
    private String city;
}
