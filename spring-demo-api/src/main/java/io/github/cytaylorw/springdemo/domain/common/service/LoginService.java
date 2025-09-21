package io.github.cytaylorw.springdemo.domain.common.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.cytaylorw.springdemo.common.email.template.EmailStringTemplate;
import io.github.cytaylorw.springdemo.common.email.template.EmailTemplate;
import io.github.cytaylorw.springdemo.core.dao.UserDao;
import io.github.cytaylorw.springdemo.core.event.DemoEventPublisher;
import io.github.cytaylorw.springdemo.core.response.ResponseMessage;
import io.github.cytaylorw.springdemo.core.security.jwt.JwtTokenUtil;
import io.github.cytaylorw.springdemo.db.auth.entity.DemoUser;
import io.github.cytaylorw.springdemo.db.auth.entity.UserPassword;
import io.github.cytaylorw.springdemo.db.auth.specification.DemoUserSpecification;
import io.github.cytaylorw.springdemo.domain.common.request.LoginRq;
import io.github.cytaylorw.springdemo.domain.common.response.LoginRsData;

/**
 * Login service
 * 
 * @author Taylor
 *
 */
@Service
public class LoginService {

    @Autowired
    private JwtTokenUtil accessTokenUtil;

    @Autowired
    private JwtTokenUtil refreshTokenUtil;

    @Autowired
    private DemoEventPublisher publisher;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public LoginRsData login(LoginRq reqest) {

        DemoUser user = this.userDao.findOne(DemoUserSpecification.equalToUsername(reqest.getUsername()))
                .orElseThrow(ResponseMessage.LOGIN_FAILED::toApiRuntimeException);

        List<UserPassword> passwordsList = this.userDao.getPasswords(user);
        if (passwordsList.isEmpty()) {
            throw ResponseMessage.LOGIN_FAILED.toApiRuntimeException();
        }

        UserPassword currentPassword = passwordsList.get(0);
        if (!this.passwordEncoder.matches(reqest.getPassword(), currentPassword.getPassword())) {
            throw ResponseMessage.LOGIN_FAILED.toApiRuntimeException();
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", user.getUsername());
        LoginRsData data = new LoginRsData();
        data.setAccessToken(this.accessTokenUtil.generateToken(claims));
        data.setRefreshToken(this.refreshTokenUtil.generateToken(claims));
        EmailTemplate template = EmailStringTemplate.builder()
                .subject("Login")
                .template(
                        "Dear [[${user.username}]], \n\n\tYou have successfully login at @[( ${#dates.format(#dates.createNow(), 'yyyy/MM/dd HH:mm:ss')} )].")
                .contextConfigurer(context -> context.setVariable("user", user))
                .build();
        publisher.emailTemplateEvent(template, List.of(user));
        return data;
    }
}
