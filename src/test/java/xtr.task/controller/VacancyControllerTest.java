package xtr.task.controller;


import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.val;

import xtr.task.App;
import xtr.task.dto.VacancyDto;
import xtr.task.fetch.Fetch;
import xtr.task.json.Address;
import xtr.task.json.EmployerJson;
import xtr.task.json.Salary;
import xtr.task.json.VacanciesHolder;
import xtr.task.json.VacancyJson;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest(classes = App.class)
@WebAppConfiguration
class VacancyControllerTest {
	private static final String API_URL = VacancyController.REST_URL;

	private MockMvc mockMvc;

	@Autowired
	private Fetch fetch;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@MockBean
	private RestTemplate restTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	private String building_1 = "building_1";
	private String city_1 = "city_1";
	private String street_1 = "street_1";

	private Address address_1 = Address.builder()
			.building(building_1)
			.city(city_1)
			.street(street_1)
			.build();

	private String employerId_1 = "522124";
	private String employerName_1 = "employerName_1";
	private EmployerJson employerJson_1 = EmployerJson.builder()
			.id(employerId_1)
			.name(employerName_1)
			.build();

	private String salaryCurrency_1 = "USD";
	private String salaryFrom_1 = "300";
	private String salaryTo_1 = "355";
	private String salaryGross_1 = "500";
	private Salary salary_1 = Salary.builder()
			.currency(salaryCurrency_1)
			.from(salaryFrom_1)
			.to(salaryTo_1)
			.gross(salaryGross_1)
			.build();

	private String vacancyName_1 = "vacancyName_1";
	private Integer vacancyId_1 = 1414;
	private VacancyJson vacancyJson_1 = VacancyJson.builder()
			.address(address_1)
			.employer(employerJson_1)
			.salary(salary_1)
			.name(vacancyName_1)
			.id(vacancyId_1)
			.build();


	private String building_2 = "building_2";
	private String city_2 = "city_2";
	private String street_2 = "street_2";

	private Address address_2 = Address.builder()
			.building(building_2)
			.city(city_2)
			.street(street_2)
			.build();

	private String employerId_2 = "141512";
	private String employerName_2 = "employerName_2";
	private EmployerJson employerJson_2 = EmployerJson.builder()
			.id(employerId_2)
			.name(employerName_2)
			.build();

	private String salaryCurrency_2 = "EUR";
	private String salaryFrom_2 = "400";
	private String salaryTo_2 = "455";
	private String salaryGross_2 = "600";
	private Salary salary_2 = Salary.builder()
			.currency(salaryCurrency_2)
			.from(salaryFrom_2)
			.to(salaryTo_2)
			.gross(salaryGross_2)
			.build();

	private String vacancyName_2 = "vacancyName_2";
	private Integer vacancyId_2 = 1414;
	private VacancyJson vacancyJson_2 = VacancyJson.builder()
			.address(address_2)
			.employer(employerJson_2)
			.salary(salary_2)
			.name(vacancyName_2)
			.id(vacancyId_2)
			.build();

	private VacanciesHolder vacanciesHolder = VacanciesHolder.builder()
			.items(Lists.newArrayList(vacancyJson_1, vacancyJson_2))
			.build();

	@BeforeEach
	public void setup() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		when(restTemplate.getForObject(anyString(), any(Class.class))).thenReturn(vacanciesHolder);
		fetch.fetchVacancies();
	}


	@Test
	void getPage() throws Exception {
		val pageRsRaw = mockMvc.perform(get(API_URL + "/pages")
				.param("pageNumber", "1")
				.param("pageSize", "1"))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();

		val contentRaw = objectMapper.readValue(pageRsRaw, ObjectNode.class).get("content").toString();

		final List<VacancyDto> pageContent = objectMapper.readValue(contentRaw, new TypeReference<List<VacancyDto>>() {
		});

		assertThat(pageContent.size()).isEqualTo(1L);

		val returnedVacancy = pageContent.get(0);
		assertThat(returnedVacancy.getName()).isEqualTo(vacancyName_1);
		assertThat(returnedVacancy.getSalaryFrom()).isEqualTo(salaryFrom_1);
		assertThat(returnedVacancy.getSalaryTo()).isEqualTo(salaryTo_1);
		assertThat(returnedVacancy.getCurrency()).isEqualTo(salaryCurrency_1);

		val returnedEmployer = returnedVacancy.getEmployer();
		assertThat(returnedEmployer.getName()).isEqualTo(employerName_1);
	}

	@Test
	void getAll() throws Exception {
		val pageRsRaw = mockMvc.perform(get(API_URL))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();

		final List<VacancyDto> pageContent = objectMapper.readValue(pageRsRaw, new TypeReference<List<VacancyDto>>() {
		});

		assertThat(pageContent.size()).isEqualTo(2L);

		assertThat(pageContent).allMatch(vacancyDto ->
				vacancyDto.getName().equals(vacancyName_1) || vacancyDto.getName().equals(vacancyName_2)
		);
	}

	@Test
	void getOne() throws Exception {
		val pageRsRaw = mockMvc.perform(get(API_URL + "/" + 1))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();

		val vacancyDto = objectMapper.readValue(pageRsRaw, VacancyDto.class);

		assertThat(vacancyDto.getName()).isEqualTo(vacancyName_1);
		assertThat(vacancyDto.getSalaryFrom()).isEqualTo(salaryFrom_1);
		assertThat(vacancyDto.getSalaryTo()).isEqualTo(salaryTo_1);
		assertThat(vacancyDto.getCurrency()).isEqualTo(salaryCurrency_1);
	}
}