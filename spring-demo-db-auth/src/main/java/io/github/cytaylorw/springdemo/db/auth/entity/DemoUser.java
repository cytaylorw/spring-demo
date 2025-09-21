package io.github.cytaylorw.springdemo.db.auth.entity;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import io.github.cytaylorw.springdemo.db.auth.entity.superclass.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
@EntityListeners({ AuditingEntityListener.class })
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
    @Size(max = 100)
    @NotNull
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
     * display name of the user
     */
    @Column(name = "user_display_name")
    private String displayName;

    /**
     * email of the user
     */
    @Size(max = 100)
    @NotNull
    @Column(name = "user_email", unique = true)
    private String email;
    
    /**
     * password records of the user
     */
    @Transient
    private List<UserPassword> passwords;
    
}
