package io.github.cytaylorw.springdemo.domain.user.request;

import io.github.cytaylorw.springdemo.db.auth.entity.DemoUser;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * Request object for PUT update {@link DemoUser}
 * 
 * @author Taylor
 *
 */
@Getter
@Setter
public class UserPutRq {

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
    @Size(max = 100)
    @NotNull
    @Schema(description = "email of the user")
    private String email;

}
