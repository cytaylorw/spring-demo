package io.github.cytaylorw.springdemo.domain.user.controller;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import io.github.cytaylorw.springdemo.core.response.ResponseBody;
import io.github.cytaylorw.springdemo.core.response.ResponseMessage;
import io.github.cytaylorw.springdemo.core.response.SimpleResponseBody;
import io.github.cytaylorw.springdemo.domain.user.request.UserCreateRq;
import io.github.cytaylorw.springdemo.domain.user.request.UserPatchRq;
import io.github.cytaylorw.springdemo.domain.user.request.UserPutRq;
import io.github.cytaylorw.springdemo.domain.user.request.UserSearchRq;
import io.github.cytaylorw.springdemo.domain.user.response.DemoUserRsData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/**
 * User controller interface
 * 
 * @author Taylor Wong
 *
 */
@Tag(name = "User", description = "Operations on user")
public interface UserContorller {


    /**
     * Search for users
     * 
     * @return
     */
    @Operation(summary = "Search for users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = ResponseMessage.Description.DEFAULT_QUERY_SUCCESS) })
    ResponseEntity<ResponseBody<Page<DemoUserRsData>>> searchUsers(
            @Valid @ParameterObject @Parameter(description = "Search parameters") UserSearchRq param,
            @ParameterObject Pageable pageable);

    /**
     * Create an user
     * 
     * @return
     */
    @Operation(summary = "Search for users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = ResponseMessage.Description.DEFAULT_CREATE_SUCCESS) })
    ResponseEntity<ResponseBody<DemoUserRsData>> createUser(
            @Valid @Parameter(description = "User details") UserCreateRq request);

    /**
     * Fetch the user by username
     * 
     * @return
     */
    @Operation(summary = "Fetch the user by username")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = ResponseMessage.Description.DEFAULT_QUERY_SUCCESS) })
    ResponseEntity<ResponseBody<DemoUserRsData>> getUser(@Parameter(description = "Usernmae") String username);

    /**
     * Entire update on the user
     * 
     * @return
     */
    @Operation(summary = "Entire update on the user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = ResponseMessage.Description.DEFAULT_UPDATE_SUCCESS) })
    ResponseEntity<ResponseBody<DemoUserRsData>> putUser(@Parameter(description = "Usernmae") String username,
            @Valid @Parameter(description = "User details") UserPutRq request);

    /**
     * Partial update on the user
     * 
     * @return
     */
    @Operation(summary = "Partial update on the user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = ResponseMessage.Description.DEFAULT_UPDATE_SUCCESS) })
    ResponseEntity<ResponseBody<DemoUserRsData>> patchUser(@Parameter(description = "Usernmae") String username,
            @Valid @Parameter(description = "User details") UserPatchRq request);

    /**
     * Delete the user
     * 
     * @return
     */
    @Operation(summary = "Delete the user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = ResponseMessage.Description.DEFAULT_DELETE_SUCCESS) })
    ResponseEntity<SimpleResponseBody> deleteUser(@Parameter(description = "Usernmae") String username);

}
