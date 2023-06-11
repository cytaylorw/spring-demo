package io.github.cytaylorw.springdemo.db.auth.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * Superclass for auditing created/updated date and user.
 * 
 * @author Taylor
 *
 */
@Getter
@Setter
@MappedSuperclass
public class Auditable extends CreatedAuditable {

    /**
     * Updated by the user
     */
    @Size(max = 100)
    @NotNull
    @Column(name = "updated_by", nullable = false)
    private String updatedBy;

    /**
     * Updated at the time
     */
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

}
