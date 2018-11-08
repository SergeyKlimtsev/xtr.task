package xtr.task.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Vacancy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String name;
	String salaryFrom;
	String salaryTo;
	String currency;
	String url;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "employer_id")
	Employer employer;
}
