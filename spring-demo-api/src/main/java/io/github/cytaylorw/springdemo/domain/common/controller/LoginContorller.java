package io.github.cytaylorw.springdemo.domain.common.controller;

import org.springframework.http.ResponseEntity;

import io.github.cytaylorw.springdemo.core.response.ResponseBody;
import io.github.cytaylorw.springdemo.core.response.ResponseMessage;
import io.github.cytaylorw.springdemo.domain.common.request.LoginRq;
import io.github.cytaylorw.springdemo.domain.common.response.LoginRsData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Login controller interface
 * 
 * @author Taylor
 *
 */
@SecurityRequirements
@Tag(name = "_Login", description = "User authentication operations")
public interface LoginContorller {


    /**
     * User login
     * 
     * @return
     */
    @Operation(summary = "User login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = ResponseMessage.Description.LOGIN_SUCCESS) })
    ResponseEntity<ResponseBody<LoginRsData>> login(LoginRq reqest);

}
