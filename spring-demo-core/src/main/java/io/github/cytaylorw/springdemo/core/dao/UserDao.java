package io.github.cytaylorw.springdemo.core.dao;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import io.github.cytaylorw.springdemo.common.repository.SpecificationProvider;
import io.github.cytaylorw.springdemo.core.response.ResponseMessage;
import io.github.cytaylorw.springdemo.db.auth.entity.DemoUser;
import io.github.cytaylorw.springdemo.db.auth.entity.UserPassword;
import io.github.cytaylorw.springdemo.db.auth.repository.DemoUserRepository;
import io.github.cytaylorw.springdemo.db.auth.repository.UserPasswordRepository;

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

    @Autowired
    private UserPasswordRepository userPasswordRepository;

    public Page<DemoUser> findAll(SpecificationProvider<DemoUser> provider, Pageable pageable) {
        return this.demoUserRepository.findAll(provider, pageable);
    }

    public Optional<DemoUser> findOne(Specification<DemoUser> params) {
        return this.demoUserRepository.findOne(params);
    }

    public <X extends Throwable> DemoUser findOneOrElseThrow(Specification<DemoUser> params,
            Supplier<? extends X> exceptionSupplier) throws X {
        return this.demoUserRepository.findOne(params).orElseThrow(exceptionSupplier);
    }

    public DemoUser findOneOrElseThrow(Specification<DemoUser> params) {
        return this.demoUserRepository.findOne(params)
                .orElseThrow(ResponseMessage.DATA_NOT_FOUND::toApiRuntimeException);
    }

    public DemoUser add(DemoUser user) {
        if (Objects.nonNull(user.getId())) {
            throw ResponseMessage.DEFAULT_SYSTEM_ERROR.toApiRuntimeException();
        }
        return this.demoUserRepository.save(user);
    }

    public DemoUser update(DemoUser user) {
        if (Objects.isNull(user.getId())) {
            throw ResponseMessage.DEFAULT_SYSTEM_ERROR.toApiRuntimeException();
        }
        return this.demoUserRepository.save(user);
    }

    public void delete(DemoUser user) {
        this.demoUserRepository.delete(user);
    }

    public List<UserPassword> getPasswords(UUID id) {
        return this.userPasswordRepository.findByUserIdOrderByCreatedAtDesc(id);
    }

    public List<UserPassword> getPasswords(DemoUser user) {
        return this.getPasswords(user.getId());
    }

    public void changePassword(UUID userId, String password) {
        UserPassword pwd = UserPassword.builder().userId(userId).password(password).build();
        this.userPasswordRepository.save(pwd);
    }

}
