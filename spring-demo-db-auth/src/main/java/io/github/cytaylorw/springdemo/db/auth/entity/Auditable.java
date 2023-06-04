package io.github.cytaylorw.springdemo.db.auth.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
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
    @Column(name = "updated_by", nullable = false, updatable = false)
    private String updatedBy;

    /**
     * Updated at the time
     */
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
