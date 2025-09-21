package io.github.cytaylorw.springdemo.domain.common.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Login response data
 *
 * @author Taylor
 */
@Data
public class LoginRsData {

    /**
     * Access Token
     */
    @Schema(description = "Access Token")
    private String accessToken;

    /**
     * Refresh Token
     */
    @Schema(description = "Refresh Token")
    private String refreshToken;

}
