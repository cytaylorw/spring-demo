package io.github.cytaylorw.springdemo.core.email;

import java.io.UnsupportedEncodingException;

import jakarta.mail.internet.InternetAddress;
import lombok.Getter;
import lombok.Setter;

/**
 * E-mail Properties
 * 
 * @author Taylor Wong
 *
 */
@Getter
@Setter
public class EmailProperties {

    /**
     * System e-mail sender
     */
    private EmailAddress sender;

    /**
     * e-mail address
     * 
     * @author Taylor Wong
     *
     */
    @Getter
    @Setter
    public static class EmailAddress {

        /**
         * the personal name
         */
        public String name;

        /**
         * the address
         */
        public String email;

        /**
         * @return
         * @throws UnsupportedEncodingException
         */
        public InternetAddress toInternetAddress() throws UnsupportedEncodingException {
            return new InternetAddress(email, name, "UTF-8");
        }
    }

}
