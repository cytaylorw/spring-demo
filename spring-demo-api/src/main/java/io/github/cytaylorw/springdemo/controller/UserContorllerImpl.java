package io.github.cytaylorw.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.cytaylorw.springdemo.core.response.ResponseBody;
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
    @GetMapping(path = "")
    public ResponseEntity<ResponseBody<List<Object>>> getUsers() {
        return ResponseMessage.DEFAULT_QUERY_SUCCESS.toApiResponseEntity(List.of());
    }

}
