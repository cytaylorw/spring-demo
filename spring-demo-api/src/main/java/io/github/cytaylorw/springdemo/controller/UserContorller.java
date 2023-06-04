package io.github.cytaylorw.springdemo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import io.github.cytaylorw.springdemo.core.response.ResponseBody;

/**
 * User controller interface
 * 
 * @author Taylor
 *
 */
public interface UserContorller {


    /**
     * Find users
     * 
     * @return
     */
    ResponseEntity<ResponseBody<List<Object>>> getUsers();

}
