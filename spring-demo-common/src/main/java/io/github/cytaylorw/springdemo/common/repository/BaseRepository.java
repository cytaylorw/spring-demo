package io.github.cytaylorw.springdemo.common.repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.lang.Nullable;

/**
 * Custom interface of JpaRepository with additional common methods.
 * 
 * @author Taylor Wong
 *
 * @param <T>  the type of the entity to handle
 * @param <ID> the type of the entity's identifier
 */
@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepositoryImplementation<T, ID> {

    /**
     * Return the result of applying the given mapping function to the entity by its
     * id.
     * 
     * @param <U>    The return type of the mapping function
     * @param id     must not be null
     * @param mapper function to apply to the entity
     * @return
     */
    <U> Optional<U> findById(ID id, Function<? super T, ? extends U> mapper);

    /**
     * Return the result of applying the given mapping function to the entity
     * matching the given {@link Specification} or {@link Optional#empty()} if none
     * found.
     * 
     * @param <U>    The return type of the mapping function
     * @param spec   can be null
     * @param mapper function to apply to the entity
     * @return
     */
    <U> Optional<U> findOne(@Nullable Specification<T> spec, Function<? super T, ? extends U> mapper);

    /**
     * Return the results of applying the given mapping function to all entities
     * 
     * @param <U>    The return type of the mapping function
     * @param mapper function to apply to each entity
     * @return
     */
    <U> List<U> findAll(Function<? super T, ? extends U> mapper);

    /**
     * Return the results of applying the given mapping function to all entities
     * with the given IDs
     * 
     * @param <U>    The return type of the mapping function
     * @param ids    must not be null nor contain any null values
     * @param mapper function to apply to each entity
     * @return
     */
    <U> List<U> findAllById(Iterable<ID> ids, Function<? super T, ? extends U> mapper);

    /**
     * Return the results of applying the given mapping function to all entities
     * sorted by the given option
     * 
     * @param <U>    The return type of the mapping function
     * @param sort   must not be null
     * @param mapper function to apply to each entity
     * @return
     */
    <U> List<U> findAll(Sort sort, Function<? super T, ? extends U> mapper);

    /**
     * Returns a Page of results of applying the given mapping function to entities
     * meeting the paging restriction provided in the Pageable object
     * 
     * @param <U>      The return type of the mapping function
     * @param pageable must not be null
     * @param mapper   function to apply to the page
     * @return
     */
    <U> Page<U> findAll(Pageable pageable, Function<? super T, ? extends U> mapper);

    /**
     * Returns the results of applying the given mapping function to all entities
     * matching the given {@link Specification}
     * 
     * @param <U>    The return type of the mapping function
     * @param spec   can be null
     * @param mapper function to apply to each entity
     * @return
     */
    <U> List<U> findAll(@Nullable Specification<T> spec, Function<? super T, ? extends U> mapper);

    /**
     * Returns a Page of results of applying the given mapping function to all
     * entities matching the given {@link Specification}
     * 
     * @param <U>      The return type of the mapping function
     * @param spec     can be null
     * @param pageable must not be null
     * @param mapper   function to apply to the page
     * @return
     */
    <U> Page<U> findAll(@Nullable Specification<T> spec, Pageable pageable, Function<? super T, ? extends U> mapper);

    /**
     * Returns the results of applying the given mapping function to all entities
     * matching the given {@link Specification} and sorted by the given option
     * 
     * @param <U>    The return type of the mapping function
     * @param spec   can be null
     * @param sort   must not be null
     * @param mapper function to apply to each entity
     * @return
     */
    <U> List<U> findAll(@Nullable Specification<T> spec, Sort sort, Function<? super T, ? extends U> mapper);

    /**
     * Returns the results of all entities matching the given {@link Specification}
     * from {@link SpecificationProvider}
     * 
     * @param <U>          The return type of the mapping function
     * @param specProvider must not be null
     * @return
     */
    List<T> findAll(SpecificationProvider<T> specProvider);

    /**
     * Returns the results of applying the given mapping function to all entities
     * matching the given {@link Specification} from {@link SpecificationProvider}
     * 
     * @param <U>          The return type of the mapping function
     * @param specProvider must not be null
     * @param mapper       function to apply to each entity
     * @return
     */
    <U> List<U> findAll(SpecificationProvider<T> specProvider, Function<? super T, ? extends U> mapper);

    /**
     * Returns a Page of results of all entities matching the given
     * {@link Specification} from {@link SpecificationProvider}
     * 
     * @param <U>          The return type of the mapping function
     * @param specProvider must not be null
     * @param pageable     must not be null
     * @return
     */
    Page<T> findAll(SpecificationProvider<T> specProvider, Pageable pageable);

    /**
     * Returns a Page of results of applying the given mapping function to all
     * entities matching the given {@link Specification} from
     * {@link SpecificationProvider}
     * 
     * @param <U>          The return type of the mapping function
     * @param specProvider must not be null
     * @param pageable     must not be null
     * @param mapper       function to apply to the page
     * @return
     */
    <U> Page<U> findAll(SpecificationProvider<T> specProvider, Pageable pageable,
            Function<? super T, ? extends U> mapper);

    /**
     * Returns the results of all entities matching the given {@link Specification}
     * from {@link SpecificationProvider} and sorted by the given option
     * 
     * @param <U>  The return type of the mapping function
     * @param spec must not be null
     * @param sort must not be null
     * @return
     */
    List<T> findAll(SpecificationProvider<T> specProvider, Sort sort);

    /**
     * Returns the results of applying the given mapping function to all entities
     * matching the given {@link Specification} from {@link SpecificationProvider}
     * and sorted by the given option
     * 
     * @param <U>    The return type of the mapping function
     * @param spec   must not be null
     * @param sort   must not be null
     * @param mapper function to apply to each entity
     * @return
     */
    <U> List<U> findAll(SpecificationProvider<T> specProvider, Sort sort, Function<? super T, ? extends U> mapper);
}
