package io.github.cytaylorw.springdemo.db.auth.entity.idclass;

import java.io.Serializable;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Taylor
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserPasswordId implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -7415045475914283463L;

    /**
     * ID of the user
     */
    private UUID userId;

    /**
     * password of the user
     */
    private String password;
}
