package io.github.cytaylorw.springdemo.config.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import io.github.cytaylorw.springdemo.core.dao.UserDao;
import io.github.cytaylorw.springdemo.db.auth.entity.DemoUser;
import io.github.cytaylorw.springdemo.db.auth.specification.DemoUserSpecification;
import lombok.extern.slf4j.Slf4j;

/**
 * Initialize data for Auth database
 * 
 * @author Taylor Wong
 *
 */
@Slf4j
@Component
public class AuthDataInitializer implements ApplicationRunner {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.initUserData();
    }

    private void initUserData() {
        String admin = "demo";
        boolean notFound = this.userDao.findOne(DemoUserSpecification.equalToUsername(admin)).isEmpty();
        if (notFound) {
            log.debug("Creating default user...");
            DemoUser user = new DemoUser();
            user.setUsername(admin);
            user.setEmail(admin + "@demo.com");
            user.setFirstName(admin);
            user.setLastName(admin);
            DemoUser saved = this.userDao.add(user);
            this.userDao.changePassword(saved.getId(), passwordEncoder.encode(admin));
        }
    }

}
