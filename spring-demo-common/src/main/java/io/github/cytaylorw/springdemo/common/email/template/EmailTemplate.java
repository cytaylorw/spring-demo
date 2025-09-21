package io.github.cytaylorw.springdemo.common.email.template;

import java.io.File;
import java.util.Collection;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * Thymeleaf E-mail Template interface
 */
public interface EmailTemplate {

    /**
     * Return the E-mail content processed by the {@code templateEngine}
     * 
     * @param templateEngine a instance of {@link TemplateEngine}
     * @return the E-mail content processed by the {@code templateEngine}
     */
    default String generate(TemplateEngine templateEngine) {
        Context context = new Context();
        this.setContext(context);
        String content = templateEngine.process(this.getTemplate(), context);
        return content;
    }

    /**
     * Configure the context to be processed. This method is called in
     * {@link EmailTemplate#generate(TemplateEngine)}. <br>
     * <br>
     * **Default is empty context.
     * 
     * @param context the context to be configured
     */
    default void setContext(Context context) {

    }

    /**
     * Is HTML template.
     * 
     * @return {@code true} if the template is HTML, else {@code false}
     */
    boolean html();

    /**
     * Get the attachments.
     * 
     * @return the attachments
     */
    Collection<File> getAttachments();

    /**
     * Set the attachments.
     * 
     * @param attachments
     */
    void setAttachments(Collection<File> attachments);

    /**
     * Get the template
     * 
     * @return the template
     */
    String getTemplate();

    /**
     * Get the subject
     * 
     * @return the subject
     */
    String getSubject();

}
