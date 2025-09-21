package io.github.cytaylorw.springdemo.db.auth.entity;

import java.util.UUID;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import io.github.cytaylorw.springdemo.db.auth.entity.idclass.UserPasswordId;
import io.github.cytaylorw.springdemo.db.auth.entity.superclass.CreatedAuditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Taylor
 *
 */
@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@IdClass(UserPasswordId.class)
@Table(schema = "auth", name = "user_passwords")
@EntityListeners({ AuditingEntityListener.class })
public class UserPassword extends CreatedAuditable {

    /**
     * ID of the user
     */
    @Id
    @NotNull
    @Column(name = "user_id")
    private UUID userId;

    /**
     * password of the user
     */
    @Id
    @Size(max = 100)
    @NotNull
    @Column(name = "user_password", unique = true)
    private String password;
}
