package io.github.cytaylorw.springdemo.domain.common.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Login request
 *
 * @author Taylor
 */
@Data
public class LoginRq {

    /**
     * Username
     */
    @Schema(description = "Username", example = "demo")
    private String username;

    /**
     * Password
     */
    @Schema(description = "Password", example = "demo")
    private String password;

}
