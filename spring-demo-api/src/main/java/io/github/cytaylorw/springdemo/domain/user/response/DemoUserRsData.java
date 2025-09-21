package io.github.cytaylorw.springdemo.domain.user.response;

import java.time.LocalDateTime;

import io.github.cytaylorw.springdemo.db.auth.entity.DemoUser;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * Response object for {@link DemoUser}
 * 
 * @author Taylor
 *
 */
@Getter
@Setter
public class DemoUserRsData {

    /**
     * username of the user
     */
    @Schema(description = "username of the user")
    private String username;

    /**
     * first name of the user
     */
    @Schema(description = "first name of the user")
    private String firstName;

    /**
     * last name of the user
     */
    @Schema(description = "last name of the user")
    private String lastName;

    /**
     * display name of the user
     */
    @Schema(description = "display name of the user")
    private String displayName;

    /**
     * email of the user
     */
    @Schema(description = "email of the user")
    private String email;

    /**
     * Updated by the user
     */
    @Schema(description = "Updated by the user")
    private String updatedBy;

    /**
     * Updated at the time
     */
    @Schema(description = "Updated at the time")
    private LocalDateTime updatedAt;

    /**
     * Created by the user
     */
    @Schema(description = "Created by the user")
    private String createdBy;

    /**
     * Created at the time
     */
    @Schema(description = "Created at the time")
    private LocalDateTime createdAt;
}
