package xtr.task.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

import java.util.List;

@Accessors(chain = true)
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String name;
	String building;
	String city;
	String street;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employer")
	List<Vacancy> vacancies;
}
