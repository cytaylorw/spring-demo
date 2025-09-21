package io.github.cytaylorw.springdemo.domain.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.cytaylorw.springdemo.core.response.ResponseBody;
import io.github.cytaylorw.springdemo.core.response.ResponseMessage;
import io.github.cytaylorw.springdemo.domain.common.request.LoginRq;
import io.github.cytaylorw.springdemo.domain.common.response.LoginRsData;
import io.github.cytaylorw.springdemo.domain.common.service.LoginService;

/**
 * Login controller implementation
 * 
 * @author Taylor Wong
 *
 */
@RestController
@RequestMapping(path = "/login")
public class LoginContorllerImpl implements LoginContorller {

    @Autowired
    private LoginService loginService;

    @Override
    @PostMapping
    public ResponseEntity<ResponseBody<LoginRsData>> login(@RequestBody LoginRq reqest) {
        LoginRsData data = this.loginService.login(reqest);
        return ResponseMessage.LOGIN_SUCCESS.toApiResponseEntity(data);
    }

}
