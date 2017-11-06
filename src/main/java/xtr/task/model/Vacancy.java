package xtr.task.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by root on 01.11.2017.
 */
@Entity
@Table(name = "vacancy")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vacancy {

    @Id
    @Column
    private Integer id;

    @Column
    private String name;

    @Column(name = "salary_from")
    private String salaryFrom;

    @Column(name = "salary_to")
    private String salaryTo;

    @Column
    private String currency;

    @Column
    private String url;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employer_id")
    private Employer employer;
}
