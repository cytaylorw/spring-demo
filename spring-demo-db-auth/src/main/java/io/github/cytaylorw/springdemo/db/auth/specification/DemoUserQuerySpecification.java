package io.github.cytaylorw.springdemo.db.auth.specification;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import io.github.cytaylorw.springdemo.db.auth.entity.DemoUser;
import io.github.cytaylorw.springdemo.db.auth.entity.DemoUser_;

/**
 * Collection of Spring Data JPA Specifications for DemoUser entity query.
 * 
 * Return null if the parameter is null or invalid.
 * 
 * @author Taylor
 *
 */
public class DemoUserQuerySpecification {

    /**
     * Return a specification equal to the username if username is not blank, else
     * return a specification of null value.
     * 
     * @param username the username of user
     * @return a specification equal to the username if username is not blank, else
     *         return a specification of null value
     */
    public static Specification<DemoUser> equalToUsername(String username) {
        return (root, query, builder) -> Optional.ofNullable(username)
                .filter(StringUtils::isNotBlank)
                .map(name -> builder.equal(root.get(DemoUser_.username), name))
                .orElse(null);
    }

    /**
     * Return a specification equal to the email if email is not blank, else return
     * a specification of null value.
     * 
     * @param email the email of user
     * @return a specification equal to the email if email is not blank, else return
     *         a specification of null value.
     */
    public static Specification<DemoUser> equalToEmail(String email) {
        return (root, query, builder) -> Optional.ofNullable(email)
                .filter(StringUtils::isNotBlank)
                .map(name -> builder.equal(root.get(DemoUser_.username), name))
                .orElse(null);
    }

}
