package io.github.cytaylorw.springdemo.db.auth.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
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
    @Column(name = "created_by", nullable = false, updatable = false)
    private String createdBy;

    /**
     * Created at the time
     */
    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
