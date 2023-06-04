package io.github.cytaylorw.springdemo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.cytaylorw.springdemo.db.auth.repository.DemoUserRepository;

/**
 * User DAO for accessing data
 * 
 * @author Taylor
 *
 */
@Component
public class UserDao {

    @Autowired
    private DemoUserRepository demoUserRepository;
}
