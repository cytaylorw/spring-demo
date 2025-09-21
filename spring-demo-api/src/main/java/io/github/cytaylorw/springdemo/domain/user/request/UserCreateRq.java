package io.github.cytaylorw.springdemo.domain.user.request;

import io.github.cytaylorw.springdemo.db.auth.entity.DemoUser;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * Request object for create {@link DemoUser}
 * 
 * @author Taylor
 *
 */
@Getter
@Setter
public class UserCreateRq extends UserPutRq {

    /**
     * username of the user
     */
    @Size(max = 100)
    @NotNull
    @Schema(description = "username of the user")
    private String username;
}
