package io.github.cytaylorw.springdemo.db.auth.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import io.github.cytaylorw.springdemo.common.repository.BaseRepository;
import io.github.cytaylorw.springdemo.db.auth.entity.UserPassword;
import io.github.cytaylorw.springdemo.db.auth.entity.idclass.UserPasswordId;

/**
 * UserPasswordId Repository
 * 
 * @author Taylor Wong
 *
 */
@Repository
public interface UserPasswordRepository extends BaseRepository<UserPassword, UserPasswordId> {

    List<UserPassword> findByUserIdOrderByCreatedAtDesc(UUID userId);
}
