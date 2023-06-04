package io.github.cytaylorw.springdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.cytaylorw.springdemo.dao.UserDao;

/**
 * User Service for business logic
 * 
 * @author Taylor
 *
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;
}
