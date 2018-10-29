package xtr.task.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.annotation.Nonnull;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import xtr.task.dto.EmployerDto;
import xtr.task.exception.NotFoundException;
import xtr.task.mappers.EmployerMapper;
import xtr.task.repository.EmployerRepository;

import static com.google.common.base.Preconditions.checkNotNull;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service
public class EmployerServiceImpl implements EmployerService {

	EmployerRepository repository;
	EmployerMapper mapper;

	static final String CACHE_NAMESPACE = "employers";

	@CacheEvict(value = CACHE_NAMESPACE, allEntries = true)
	@Override
	public EmployerDto add(@NonNull EmployerDto employer) {
		return Optional.of(employer)
				.map(mapper::toEntity)
				.map(repository::save)
				.map(mapper::toDto)
				.get();
	}

	@CacheEvict(value = CACHE_NAMESPACE, allEntries = true)
	@Override
	public List<EmployerDto> addAll(@NonNull List<EmployerDto> employers) {
		return Optional.of(employers)
				.map(mapper::toEntity)
				.map(repository::saveAll)
				.map(mapper::toDto)
				.get();
	}

	@CacheEvict(value = CACHE_NAMESPACE, allEntries = true)
	@Override
	public void update(@NonNull EmployerDto employer) throws NotFoundException {
		checkNotNull(employer.getId(), "Id of employer should be not null");
		repository.save(mapper.toEntity(employer));
	}

	@CacheEvict(value = CACHE_NAMESPACE, allEntries = true)
	@Override
	public void delete(int id) throws NotFoundException {
		repository.deleteById(id);
	}

	@Cacheable(CACHE_NAMESPACE)
	@Nonnull
	@Override
	public EmployerDto get(int id) throws NotFoundException {
		return repository.findById(id)
				.map(mapper::toDto)
				.orElseThrow(() -> new NotFoundException(String.format("Employer for id=n% doesn't found", id)));
	}

	@Cacheable(CACHE_NAMESPACE)
	@Override
	public List<EmployerDto> getAll() {
		return StreamSupport.stream(repository.findAll().spliterator(), false)
				.map(mapper::toDto)
				.collect(Collectors.toList());
	}

	@Cacheable(CACHE_NAMESPACE)
	@Override
	public Page<EmployerDto> getPage(Integer pageNumber, Integer pageSize) {
		return repository.findAll(PageRequest.of(pageNumber - 1, pageSize))
				.map(mapper::toDto);
	}
}
