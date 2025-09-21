package io.github.cytaylorw.springdemo.db.auth.repository;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import io.github.cytaylorw.springdemo.common.repository.BaseRepository;
import io.github.cytaylorw.springdemo.db.auth.entity.DemoUser;

/**
 * User Repository
 * 
 * @author Taylor Wong
 *
 */
@Repository
public interface DemoUserRepository extends BaseRepository<DemoUser, UUID> {

}
