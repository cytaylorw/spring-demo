package io.github.cytaylorw.springdemo.core.email;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Set;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.StringTemplateResolver;

import lombok.extern.slf4j.Slf4j;

/**
 * Default e-mail configuration with Thyemleaf template.
 * 
 * @author Taylor
 *
 */
@Slf4j
@ConditionalOnProperty( //
        prefix = DefaultEmailConfiguration.PROPERTY_PREFIX, name = "enable", havingValue = "true", matchIfMissing = false)
@Configuration
public class DefaultEmailConfiguration {

    public static final String PROPERTY_PREFIX = "spring.email";

    public static final String[] MESSAGE_SOURCE_BASENAME_STRINGS = { "mail/MailMessages" };

    public static final String RESOLVER_PREFIX_STRING = "templates/mail/";

    public static final Set<String> TEXT_RESOLVER_PATTERNS = Collections.singleton("text/*");

    public static final Set<String> HTML_RESOLVER_PATTERNS = Collections.singleton("html/*");

    /**
     * {@link EmailProperties} bean.
     * 
     * @return {@link EmailProperties}
     */
    @ConfigurationProperties(PROPERTY_PREFIX + ".property")
    @Bean
    public EmailProperties emailProperties() {
        return new EmailProperties();
    }

    /**
     * e-mail sender bean
     * 
     * @param templateEngine e-mail template engine bean
     * @param mailSender     {@link JavaMailSender}
     * @param props          {@link EmailProperties}
     * @return e-mail sender
     * @throws UnsupportedEncodingException
     */
    @Bean
    public DemoEmailSender emailSender(@Qualifier("emailTemplateEngine") TemplateEngine templateEngine,
            JavaMailSender mailSender, EmailProperties props) throws UnsupportedEncodingException {
        return new DemoEmailSender(mailSender, props.getSender().toInternetAddress(), templateEngine);
    }

    /**
     * The e-mail message source bean.
     * 
     * @return e-mail message source bean
     */
    @Bean
    public ResourceBundleMessageSource emailMessageSource() {
        return DefaultEmailConfiguration.messageSource(MESSAGE_SOURCE_BASENAME_STRINGS);
    }

    /**
     * The e-mail template engine bean.
     * 
     * @return e-mail template engine
     */
    @Bean
    public TemplateEngine emailTemplateEngine() {
        final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.addDialect(new Java8TimeDialect());
        // Resolver for TEXT emails
        templateEngine.addTemplateResolver(textTemplateResolver(1, false));
        // Resolver for HTML emails (except the editable one)
        templateEngine.addTemplateResolver(htmlTemplateResolver(2, false));
        // Resolver for HTML editable emails (which will be treated as a String)
        templateEngine.addTemplateResolver(stringTemplateResolver(3, false));
        // Message source, internationalization specific to emails
        templateEngine.setTemplateEngineMessageSource(emailMessageSource());
        return templateEngine;
    }

    /**
     * Return a {@link ResourceBundleMessageSource} configured with specified
     * basenames.
     * 
     * @param basenames an array of basenames
     * @return a {@link ResourceBundleMessageSource} configured with specified
     *         basenames
     */
    public static ResourceBundleMessageSource messageSource(String... basenames) {
        final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames(basenames);
        return messageSource;
    }

    /**
     * Return an {@link ITemplateResolver} for text templates configured with
     * default prefix and patterns.
     * 
     * @param order    the order of the resolver
     * @param cachable the cacheable flag
     * @return an {@link ITemplateResolver} for text templates configured with
     *         default prefix and patterns
     */
    public static ITemplateResolver textTemplateResolver(Integer order, boolean cachable) {
        final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setOrder(order);
        templateResolver.setResolvablePatterns(TEXT_RESOLVER_PATTERNS);
        templateResolver.setPrefix(RESOLVER_PREFIX_STRING);
        templateResolver.setSuffix(".txt");
        templateResolver.setTemplateMode(TemplateMode.TEXT);
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setCacheable(cachable);
        return templateResolver;
    }

    /**
     * Return an {@link ITemplateResolver} for html templates configured with
     * default prefix and patterns.
     * 
     * @param order    the order of the resolver
     * @param cachable the cacheable flag
     * @return an {@link ITemplateResolver} for html templates configured with
     *         default prefix and patterns
     */
    public static ITemplateResolver htmlTemplateResolver(Integer order, boolean cachable) {
        final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setOrder(order);
        templateResolver.setResolvablePatterns(HTML_RESOLVER_PATTERNS);
        templateResolver.setPrefix(RESOLVER_PREFIX_STRING);
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setCacheable(cachable);
        return templateResolver;
    }

    /**
     * Return an {@link ITemplateResolver} for string templates in HTML TemplateMode
     * configured with default prefix and patterns.
     * 
     * @param order    the order of the resolver
     * @param cachable the cacheable flag
     * @return an {@link ITemplateResolver} for string templates in HTML
     *         TemplateMode configured with default prefix and patterns
     */
    public static ITemplateResolver stringTemplateResolver(Integer order, boolean cachable) {
        final StringTemplateResolver templateResolver = new StringTemplateResolver();
        templateResolver.setOrder(order);
        // No resolvable pattern, will simply process as a String template everything
        // not previously matched
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCacheable(cachable);
        return templateResolver;
    }
}
