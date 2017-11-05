package xtr.task.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Created by root on 01.11.2017.
 */
@Entity
@Table(name = "employer")
@Data
@Builder
@NoArgsConstructor
public class Employer  {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String building;

    @Column
    private String city;

    @Column
    private String street;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Vacancy> vacancies;
}
