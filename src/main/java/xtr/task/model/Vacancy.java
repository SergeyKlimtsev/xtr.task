package xtr.task.model;

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
public class Vacancy {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private Employer employer;
}
