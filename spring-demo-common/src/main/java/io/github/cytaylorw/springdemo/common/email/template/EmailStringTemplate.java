package io.github.cytaylorw.springdemo.common.email.template;

import java.io.File;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Consumer;

import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.StringTemplateResolver;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Thymeleaf E-mail String Template for {@link StringTemplateResolver}
 * 
 * @author Taylor Wong
 *
 */
@Getter
@Setter
@Builder
public class EmailStringTemplate implements EmailTemplate {

    /**
     * E-mail subject
     */
    private final String subject;

    /**
     * E-mail text/HTML template
     */
    private final String template;

    /**
     * Is this a HTML template?
     */
    private final boolean isHtml;

    /**
     * Configure the Thymeleaf {@link Context}
     */
    private final Consumer<Context> contextConfigurer;

    /**
     * Attachments
     */
    private Collection<File> attachments;

    @Override
    public final void setContext(Context context) {
        if (Objects.nonNull(this.contextConfigurer)) {
            this.contextConfigurer.accept(context);
        }
    }

    @Override
    public boolean html() {
        return this.isHtml;
    }

}
