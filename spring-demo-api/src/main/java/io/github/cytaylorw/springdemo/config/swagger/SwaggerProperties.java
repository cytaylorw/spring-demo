package io.github.cytaylorw.springdemo.config.swagger;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Swagger properties
 * 
 * @author Taylor Wong
 *
 */
@Getter
@Setter
public class SwaggerProperties {

    /**
     * List of servers
     */
    private List<Server> servers;

    /**
     * Server properties
     * 
     * @author Taylor Wong
     *
     */
    @Getter
    @Setter
    public static class Server {

        /**
         * URL of the server
         */
        private String url;

        /**
         * Description of the server
         */
        private String description;
    }
}
