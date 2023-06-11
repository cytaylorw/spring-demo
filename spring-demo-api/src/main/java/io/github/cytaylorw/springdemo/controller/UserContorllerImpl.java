package io.github.cytaylorw.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.cytaylorw.springdemo.core.response.ApiResponseEntity;
import io.github.cytaylorw.springdemo.core.response.ResponseMessage;
import io.github.cytaylorw.springdemo.service.UserService;

/**
 * User controller implementation
 * 
 * @author Taylor
 *
 */
@RestController
@RequestMapping(path = "/users")
public class UserContorllerImpl implements UserContorller {

    @Autowired
    private UserService userService;

    @Override
    @GetMapping
    public ApiResponseEntity<List<Object>> findUsers() {
        return ResponseMessage.DEFAULT_QUERY_SUCCESS.toApiResponseEntity(List.of());
    }

}
