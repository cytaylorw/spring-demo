package io.github.cytaylorw.springdemo.common.email;

import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.TemplateEngine;

import io.github.cytaylorw.springdemo.common.email.template.EmailTemplate;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;


/**
 * Thymeleaf e-mail template sender class with builder.
 * 
 * @author Taylor Wong
 *
 */
@Slf4j
public class EmailTemplateSender extends EmailSender {

    /**
     * TemplateEngine
     */
    protected final TemplateEngine templateEngine;

    /**
     * constructor
     * 
     * @param mailSender
     * @param sender
     * @param templateEngine
     */
    public EmailTemplateSender(JavaMailSender mailSender, InternetAddress sender, TemplateEngine templateEngine) {
        super(mailSender, sender);
        this.templateEngine = templateEngine;
    }

    /**
     * Initialize the {@link TemplateMimeMessageBuilder} instance
     * 
     * @return {@link TemplateMimeMessageBuilder} instance
     */
    public TemplateMimeMessageBuilder templateBuilder() {
        return this.new TemplateMimeMessageBuilder();
    }

    /**
     * Create an e-mail message from {@link EmailTemplate}
     * 
     * @param template e-mail template
     * @param to       TO recipients
     * @return MIME style e-mail message
     */
    public MimeMessage createMessage(EmailTemplate template, String... to) {
        return this.templateBuilder().addTo(to).setTemplate(template).build();
    }

    /**
     * Create and send an e-mail message from {@link EmailTemplate}
     * 
     * @param template e-mail template
     * @param to       TO recipients
     */
    public void sendMessage(EmailTemplate template, String... to) {
        MimeMessage message = this.createMessage(template, to);
        this.mailSender.send(message);
    }

    /**
     * the builder class for {@link EmailTemplateSender}
     * 
     * @author Taylor Wong
     *
     */
    public class TemplateMimeMessageBuilder extends AbstractTemplateMimeMessageBuilder<TemplateMimeMessageBuilder> {

        @Override
        protected TemplateMimeMessageBuilder self() {
            return this;
        }

        @Override
        protected JavaMailSender mailSender() {
            return EmailTemplateSender.this.mailSender;
        }

        @Override
        protected InternetAddress sender() {
            return EmailTemplateSender.this.sender;
        }

        @Override
        protected TemplateEngine templateEngine() {
            return EmailTemplateSender.this.templateEngine;
        }

    }

    /**
     * Base abstract class for
     * {@link EmailTemplateSender.TemplateMimeMessageBuilder}
     * 
     * @author Taylor Wong
     *
     * @param <B> type of builder instance
     */
    public abstract class AbstractTemplateMimeMessageBuilder<B extends AbstractTemplateMimeMessageBuilder<B>>
            extends AbstractMimeMessageBuilder<B> {

        /**
         * Return the {@link TemplateEngine} instance
         * 
         * @return {@link TemplateEngine} instance
         */
        protected abstract TemplateEngine templateEngine();

        /**
         * Constructor
         */
        public AbstractTemplateMimeMessageBuilder() {
            super();
        }

        /**
         * Add the template properties (subject, text/html, and attachments) to the
         * message. The text/html content is generate from the TemplateEngine.
         * 
         * @param template {@link EmailTemplate}
         * @return the builder instance
         */
        public B setTemplate(EmailTemplate template) {
            final String content = template.generate(this.templateEngine());
            log.debug(content);
            final Collection<File> attachments = Optional.ofNullable(template.getAttachments())
                    .orElse(Collections.emptyList());
            return this.setSubject(template.getSubject()).setText(content, template.html()).addAttachment(attachments);
        }

    }
}
