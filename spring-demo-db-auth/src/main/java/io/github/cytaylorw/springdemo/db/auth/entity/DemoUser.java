package io.github.cytaylorw.springdemo.db.auth.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * User entity
 * 
 * @author Taylor
 *
 */
@Getter
@Setter
@Entity
@Table(schema = "auth", name = "users")
public class DemoUser extends Auditable {

    /**
     * ID of the user
     */
    @Id
    @GeneratedValue
    @Column(name = "user_id", updatable = false)
    private UUID id;

    /**
     * username of the user
     */
    @Column(name = "user_username", unique = true)
    private String username;

    /**
     * first name of the user
     */
    @Column(name = "user_first_name")
    private String firstName;

    /**
     * last name of the user
     */
    @Column(name = "user_last_name")
    private String lastName;

    /**
     * email of the user
     */
    @Column(name = "user_email", unique = true)
    private String email;
}
