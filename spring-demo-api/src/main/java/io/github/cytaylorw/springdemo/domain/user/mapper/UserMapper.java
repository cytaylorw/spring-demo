package io.github.cytaylorw.springdemo.domain.user.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import io.github.cytaylorw.springdemo.db.auth.entity.DemoUser;
import io.github.cytaylorw.springdemo.domain.user.request.UserCreateRq;
import io.github.cytaylorw.springdemo.domain.user.request.UserPatchRq;
import io.github.cytaylorw.springdemo.domain.user.request.UserPutRq;
import io.github.cytaylorw.springdemo.domain.user.response.DemoUserRsData;

/**
 * User Mapper class
 * 
 * @author Taylor Wong
 *
 */
@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    DemoUserRsData toDemoUserRsData(DemoUser user);

    List<DemoUserRsData> toDemoUserRsData(List<DemoUser> user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "passwords", ignore = true)
    DemoUser toDemoUser(UserCreateRq request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "passwords", ignore = true)
    DemoUser toDemoUser(@MappingTarget DemoUser user, UserPutRq request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "passwords", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    DemoUser toDemoUser(@MappingTarget DemoUser user, UserPatchRq request);
}
