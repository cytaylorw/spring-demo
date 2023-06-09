package io.github.cytaylorw.springdemo.common.repository;

import org.springframework.data.jpa.domain.Specification;

/**
 * A provider that produces a specification for the entity
 * 
 * @author Taylor Wong
 *
 * @param <E> type of the entity
 */
public interface SpecificationProvider<E> {

    /**
     * Return a specification for the entity
     * 
     * @return a specification for the entity
     */
    Specification<E> toSpecification();
}
