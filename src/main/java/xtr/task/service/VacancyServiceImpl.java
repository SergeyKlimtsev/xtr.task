package xtr.task.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.annotation.Nonnull;
import javax.persistence.EntityNotFoundException;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import xtr.task.dto.VacancyDto;
import xtr.task.mappers.VacancyMapper;
import xtr.task.model.Vacancy;
import xtr.task.repository.VacancyRepository;

import static com.google.common.base.Preconditions.checkNotNull;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class VacancyServiceImpl implements VacancyService {

	VacancyRepository repository;
	VacancyMapper mapper;

	static final String CACHE_NAMESPACE = "vacancies";

	@CacheEvict(value = CACHE_NAMESPACE, allEntries = true)
	@Transactional
	@Override
	public VacancyDto add(@NonNull VacancyDto vacancy) {
		return Optional.of(vacancy)
				.map(mapper::toEntity)
				.map(repository::save)
				.map(mapper::toDto)
				.get();
	}

	@CacheEvict(value = CACHE_NAMESPACE, allEntries = true)
	@Transactional
	@Override
	public List<VacancyDto> addAll(@NonNull List<VacancyDto> vacancies) {
		return Optional.of(vacancies)
				.map(mapper::toEntity)
				.map(repository::saveAll)
				.map(Lists::newArrayList)
				.map(mapper::toDto)
				.get();
	}

	@CacheEvict(value = CACHE_NAMESPACE, allEntries = true)
	@Transactional
	@Override
	public List<VacancyDto> addAll(Iterable<Vacancy> vacancies) {
		return Optional.of(vacancies)
				.map(repository::saveAll)
				.map(mapper::toDto)
				.get();
	}

	@CacheEvict(value = CACHE_NAMESPACE, allEntries = true)
	@Override
	public void update(@NonNull VacancyDto vacancy) {
		checkNotNull(vacancy.getId(), "Id of vacancy should be not null");
		repository.save(mapper.toEntity(vacancy));
	}

	@CacheEvict(value = CACHE_NAMESPACE, allEntries = true)
	@Override
	public void delete(int id) {
		repository.deleteById(id);
	}

	@Cacheable(CACHE_NAMESPACE)
	@Nonnull
	@Override
	public VacancyDto get(int id) {
		return repository.findById(id)
				.map(mapper::toDto)
				.orElseThrow(() -> new EntityNotFoundException(String.format("Vacancy for id=n% doesn't found", id)));
	}

	@Cacheable(CACHE_NAMESPACE)
	@Override
	public List<VacancyDto> getAll() {
		return StreamSupport.stream(repository.findAll().spliterator(), false)
				.map(mapper::toDto)
				.collect(Collectors.toList());
	}

	@Cacheable(CACHE_NAMESPACE)
	@Override
	public Page<VacancyDto> getPage(Integer pageNumber, Integer pageSize) {
		return repository.findAll(PageRequest.of(pageNumber - 1, pageSize))
				.map(mapper::toDto);
	}


}
