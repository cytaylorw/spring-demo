package io.github.cytaylorw.springdemo.common.repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import jakarta.persistence.EntityManager;

/**
 * @author Taylor Wong
 *
 * @param <T>  the type of the entity to handle
 * @param <ID> the type of the entity's identifier
 */
public class BaseRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

	/**
	 * Creates a new {@link DemoBaseRepository} to manage objects of the given
	 * {@link JpaEntityInformation}.
	 *
	 * @param entityInformation must not be {@literal null}.
	 * @param entityManager     must not be {@literal null}.
	 */
	public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
	}

	/**
	 * Creates a new {@link DemoBaseRepository} to manage objects of the given
	 * domain type.
	 *
	 * @param domainClass must not be {@literal null}.
	 * @param em          must not be {@literal null}.
	 */
	public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
		this(JpaEntityInformationSupport.getEntityInformation(domainClass, em), em);
	}

	@Override
	public <U> Optional<U> findById(ID id, Function<? super T, ? extends U> mapper) {
		Objects.requireNonNull(mapper);
		return this.findById(id).map(mapper);
	}

	@Override
	public <U> Optional<U> findOne(Specification<T> spec, Function<? super T, ? extends U> mapper) {
		Objects.requireNonNull(mapper);
		return this.findOne(spec).map(mapper);
	}

	@Override
	public <U> List<U> findAll(Function<? super T, ? extends U> mapper) {
		Objects.requireNonNull(mapper);
		return this.findAll().stream().map(mapper).collect(Collectors.toList());
	}

	@Override
	public <U> List<U> findAllById(Iterable<ID> ids, Function<? super T, ? extends U> mapper) {
		Objects.requireNonNull(mapper);
		return this.findAllById(ids).stream().map(mapper).collect(Collectors.toList());
	}

	@Override
	public <U> List<U> findAll(Sort sort, Function<? super T, ? extends U> mapper) {
		Objects.requireNonNull(mapper);
		return this.findAll(sort).stream().map(mapper).collect(Collectors.toList());
	}

	@Override
	public <U> Page<U> findAll(Pageable pageable, Function<? super T, ? extends U> mapper) {
		Objects.requireNonNull(mapper);
		return this.findAll(pageable).map(mapper);
	}

	@Override
	public <U> List<U> findAll(Specification<T> spec, Function<? super T, ? extends U> mapper) {
		Objects.requireNonNull(mapper);
		return this.findAll(spec).stream().map(mapper).collect(Collectors.toList());
	}

	@Override
	public <U> Page<U> findAll(Specification<T> spec, Pageable pageable, Function<? super T, ? extends U> mapper) {
		Objects.requireNonNull(mapper);
		return this.findAll(spec, pageable).map(mapper);
	}

	@Override
	public <U> List<U> findAll(Specification<T> spec, Sort sort, Function<? super T, ? extends U> mapper) {
		Objects.requireNonNull(mapper);
		return this.findAll(spec, sort).stream().map(mapper).collect(Collectors.toList());
	}

}
