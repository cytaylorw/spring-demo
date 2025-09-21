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
public class DemoUserSpecification {

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
     * Return a specification like the username ignoring case if username is not
     * blank, else return a specification of null value.
     * 
     * @param username the username of user
     * @return a specification like the username if username is not blank, else
     *         return a specification of null value
     */
    public static Specification<DemoUser> likeUsernameIgnoreCase(String username) {
        return (root, query, builder) -> Optional.ofNullable(username)
                .filter(StringUtils::isNotBlank)
                .map(String::toUpperCase)
                .map(name -> builder.like(builder.upper(root.get(DemoUser_.username)), "%" + name + "%"))
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
                .map(name -> builder.equal(root.get(DemoUser_.email), name))
                .orElse(null);
    }

    /**
     * Return a specification like the email ignoring case if email is not blank,
     * else return a specification of null value.
     * 
     * @param email the email of user
     * @return a specification like the email if email is not blank, else return a
     *         specification of null value
     */
    public static Specification<DemoUser> likeEmailIgnoreCase(String email) {
        return (root, query, builder) -> Optional.ofNullable(email)
                .filter(StringUtils::isNotBlank)
                .map(String::toUpperCase)
                .map(mail -> builder.like(builder.upper(root.get(DemoUser_.email)), "%" + mail + "%"))
                .orElse(null);
    }

    /**
     * Return a specification like the first name ignoring case if first name is not
     * blank, else return a specification of null value.
     * 
     * @param firstName the first name of user
     * @return a specification like the first name if first name is not blank, else
     *         return a specification of null value
     */
    public static Specification<DemoUser> likeFirstNameIgnoreCase(String firstName) {
        return (root, query, builder) -> Optional.ofNullable(firstName)
                .filter(StringUtils::isNotBlank)
                .map(String::toUpperCase)
                .map(name -> builder.like(builder.upper(root.get(DemoUser_.firstName)), "%" + name + "%"))
                .orElse(null);
    }

    /**
     * Return a specification like the last name ignoring case if last name is not
     * blank, else return a specification of null value.
     * 
     * @param lastName the last name of user
     * @return a specification like the last name if last name is not blank, else
     *         return a specification of null value
     */
    public static Specification<DemoUser> likeLastNameIgnoreCase(String lastName) {
        return (root, query, builder) -> Optional.ofNullable(lastName)
                .filter(StringUtils::isNotBlank)
                .map(String::toUpperCase)
                .map(name -> builder.like(builder.upper(root.get(DemoUser_.lastName)), "%" + name + "%"))
                .orElse(null);
    }

}
