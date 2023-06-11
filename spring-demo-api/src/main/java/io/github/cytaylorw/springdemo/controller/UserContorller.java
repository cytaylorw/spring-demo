package io.github.cytaylorw.springdemo.controller;

import java.util.List;

import io.github.cytaylorw.springdemo.core.response.ApiResponseEntity;

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
    ApiResponseEntity<List<Object>> findUsers();

}
