package io.github.cytaylorw.springdemo.db.auth.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * Superclass for auditing created date and user.
 * 
 * @author Taylor
 *
 */
@Getter
@Setter
@MappedSuperclass
public class CreatedAuditable {

    /**
     * Created by the user
     */
    @Size(max = 100)
    @NotNull
    @Column(name = "created_by", nullable = false, updatable = false)
    private String createdBy;

    /**
     * Created at the time
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

}
