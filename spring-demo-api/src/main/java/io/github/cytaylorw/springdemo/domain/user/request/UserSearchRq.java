package io.github.cytaylorw.springdemo.domain.user.request;

import org.springframework.data.jpa.domain.Specification;

import io.github.cytaylorw.springdemo.common.repository.SpecificationProvider;
import io.github.cytaylorw.springdemo.db.auth.entity.DemoUser;
import io.github.cytaylorw.springdemo.db.auth.specification.DemoUserSpecification;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * Response object for {@link DemoUser}
 * 
 * @author Taylor
 *
 */
@Getter
@Setter
public class UserSearchRq implements SpecificationProvider<DemoUser> {

    /**
     * username of the user
     */
    @Schema(description = "username of the user(*)")
    private String username;

    /**
     * first name of the user
     */
    @Schema(description = "first name of the user(*)")
    private String firstName;

    /**
     * last name of the user
     */
    @Schema(description = "last name of the user(*)")
    private String lastName;

    /**
     * email of the user
     */
    @Schema(description = "email of the user(*)")
    private String email;

    @Override
    public Specification<DemoUser> toSpecification() {
        return Specification.allOf(DemoUserSpecification.likeUsernameIgnoreCase(username),
                DemoUserSpecification.likeEmailIgnoreCase(email),
                DemoUserSpecification.likeFirstNameIgnoreCase(firstName),
                DemoUserSpecification.likeLastNameIgnoreCase(lastName));
    }
}
