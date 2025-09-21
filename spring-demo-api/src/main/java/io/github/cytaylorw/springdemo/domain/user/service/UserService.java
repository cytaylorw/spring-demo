package io.github.cytaylorw.springdemo.domain.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.github.cytaylorw.springdemo.core.dao.UserDao;
import io.github.cytaylorw.springdemo.db.auth.entity.DemoUser;
import io.github.cytaylorw.springdemo.db.auth.specification.DemoUserSpecification;
import io.github.cytaylorw.springdemo.domain.user.mapper.UserMapper;
import io.github.cytaylorw.springdemo.domain.user.request.UserCreateRq;
import io.github.cytaylorw.springdemo.domain.user.request.UserPatchRq;
import io.github.cytaylorw.springdemo.domain.user.request.UserPutRq;
import io.github.cytaylorw.springdemo.domain.user.request.UserSearchRq;
import io.github.cytaylorw.springdemo.domain.user.response.DemoUserRsData;

/**
 * User Service for business logic
 * 
 * @author Taylor Wong
 *
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public Page<DemoUserRsData> search(UserSearchRq param, Pageable pageable) {
        return this.userDao.findAll(param, pageable).map(UserMapper.INSTANCE::toDemoUserRsData);
    }

    public DemoUserRsData create(UserCreateRq request) {
        DemoUser saved = this.userDao.add(UserMapper.INSTANCE.toDemoUser(request));
        return UserMapper.INSTANCE.toDemoUserRsData(saved);
    }

    public DemoUserRsData find(String username) {
        DemoUser user = this.userDao.findOneOrElseThrow(DemoUserSpecification.equalToUsername(username));
        return UserMapper.INSTANCE.toDemoUserRsData(user);
    }

    public DemoUserRsData put(String username, UserPutRq request) {
        DemoUser user = this.userDao.findOneOrElseThrow(DemoUserSpecification.equalToUsername(username));
        DemoUser saved = this.userDao.update(UserMapper.INSTANCE.toDemoUser(user, request));
        return UserMapper.INSTANCE.toDemoUserRsData(saved);
    }

    public DemoUserRsData patch(String username, UserPatchRq request) {
        DemoUser user = this.userDao.findOneOrElseThrow(DemoUserSpecification.equalToUsername(username));
        DemoUser saved = this.userDao.update(UserMapper.INSTANCE.toDemoUser(user, request));
        return UserMapper.INSTANCE.toDemoUserRsData(saved);
    }

    public void delete(String username) {
        DemoUser user = this.userDao.findOneOrElseThrow(DemoUserSpecification.equalToUsername(username));
        this.userDao.delete(user);
    }

}
