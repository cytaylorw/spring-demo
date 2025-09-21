package io.github.cytaylorw.springdemo.core.email;

import java.io.UnsupportedEncodingException;
import java.util.Collection;

import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.TemplateEngine;

import io.github.cytaylorw.springdemo.common.email.EmailTemplateSender;
import io.github.cytaylorw.springdemo.common.email.MessagingRuntimeException;
import io.github.cytaylorw.springdemo.common.email.template.EmailTemplate;
import io.github.cytaylorw.springdemo.db.auth.entity.DemoUser;
import io.micrometer.common.util.StringUtils;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;


/**
 * Demo e-mail template sender class with builder.
 * 
 * @author Taylor Wong
 *
 */
public class DemoEmailSender extends EmailTemplateSender {

    /**
     * constructor
     * 
     * @param mailSender
     * @param sender
     * @param templateEngine
     */
    public DemoEmailSender(JavaMailSender mailSender, InternetAddress sender, TemplateEngine templateEngine) {
        super(mailSender, sender, templateEngine);
    }

    /**
     * Initialize the {@link DemoMimeMessageBuilder} instance
     * 
     * @return {@link DemoMimeMessageBuilder} instance
     */
    public DemoMimeMessageBuilder demoBuilder() {
        return this.new DemoMimeMessageBuilder();
    }

    /**
     * Create a text message
     * 
     * @param subject e-mail subject
     * @param text    text content
     * @param to      TO recipients
     * @return MIME style e-mail message
     */
    public MimeMessage createMessage(String subject, String text, DemoUser... to) {
        return this.demoBuilder().addTo(to).setSubject(subject).setText(text).build();
    }

    /**
     * Create and send a text message
     * 
     * @param subject e-mail subject
     * @param text    text content
     * @param to      TO recipients
     */
    public void sendMessage(String subject, String text, DemoUser... to) {
        MimeMessage message = this.createMessage(subject, text, to);
        this.mailSender.send(message);
    }

    /**
     * Create a text message
     * 
     * @param subject e-mail subject
     * @param text    text content
     * @param to      TO recipients
     * @return MIME style e-mail message
     */
    public MimeMessage createMessage(String subject, String text, Collection<DemoUser> to) {
        return this.demoBuilder().addTo(to).setSubject(subject).setText(text).build();
    }

    /**
     * Create and send a text message
     * 
     * @param subject e-mail subject
     * @param text    text content
     * @param to      TO recipients
     */
    public void sendMessage(String subject, String text, Collection<DemoUser> to) {
        MimeMessage message = this.createMessage(subject, text, to);
        this.mailSender.send(message);
    }

    /**
     * Create an e-mail message from {@link EmailTemplate}
     * 
     * @param template e-mail template
     * @param to       TO recipients
     * @return MIME style e-mail message
     */
    public MimeMessage createMessage(EmailTemplate template, DemoUser... to) {
        return this.demoBuilder().setFrom(sender).addTo(to).setTemplate(template).build();
    }

    /**
     * Create and send an e-mail message from {@link EmailTemplate}
     * 
     * @param template e-mail template
     * @param to       TO recipients
     */
    public void sendMessage(EmailTemplate template, DemoUser... to) {
        MimeMessage message = this.createMessage(template, to);
        this.mailSender.send(message);
    }

    /**
     * Create an e-mail message from {@link EmailTemplate}
     * 
     * @param template e-mail template
     * @param to       TO recipients
     * @return MIME style e-mail message
     */
    public MimeMessage createMessage(EmailTemplate template, Collection<DemoUser> to) {
        return this.demoBuilder().setFrom(sender).addTo(to).setTemplate(template).build();
    }

    /**
     * Create and send an e-mail message from {@link EmailTemplate}
     * 
     * @param template e-mail template
     * @param to       TO recipients
     */
    public void sendMessage(EmailTemplate template, Collection<DemoUser> to) {
        MimeMessage message = this.createMessage(template, to);
        this.mailSender.send(message);
    }

    /**
     * Create an e-mail message from {@link EmailTemplate}
     * 
     * @param template e-mail template
     * @param to       TO recipients
     * @param cc       CC recipients
     * @return MIME style e-mail message
     */
    public MimeMessage createMessage(EmailTemplate template, Collection<DemoUser> to, Collection<DemoUser> cc) {
        return this.demoBuilder().setFrom(sender).addTo(to).addCc(cc).setTemplate(template).build();
    }

    /**
     * Create and send an e-mail message from {@link EmailTemplate}
     * 
     * @param template e-mail template
     * @param to       TO recipients
     * @param cc       CC recipients
     */
    public void sendMessage(EmailTemplate template, Collection<DemoUser> to, Collection<DemoUser> cc) {
        MimeMessage message = this.createMessage(template, to, cc);
        this.mailSender.send(message);
    }

    /**
     * Create an e-mail message from {@link EmailTemplate}
     * 
     * @param template e-mail template
     * @param to       TO recipients
     * @param cc       CC recipients
     * @param bcc      BCC recipients
     * @return MIME style e-mail message
     */
    public MimeMessage createMessage(EmailTemplate template, Collection<DemoUser> to, Collection<DemoUser> cc,
            Collection<DemoUser> bcc) {
        return this.demoBuilder().setFrom(sender).addTo(to).addCc(cc).addBcc(bcc).setTemplate(template).build();
    }

    /**
     * Create and send an e-mail message from {@link EmailTemplate}
     * 
     * @param template e-mail template
     * @param to       TO recipients
     * @param cc       CC recipients
     * @param bcc      BCC recipients
     */
    public void sendMessage(EmailTemplate template, Collection<DemoUser> to, Collection<DemoUser> cc,
            Collection<DemoUser> bcc) {
        MimeMessage message = this.createMessage(template, to, cc, bcc);
        this.mailSender.send(message);
    }

    /**
     * the builder class for {@link DemoEmailSender}
     * 
     * @author Taylor Wong
     *
     */
    public class DemoMimeMessageBuilder extends AbstractDemoMimeMessageBuilder<DemoMimeMessageBuilder> {

        @Override
        protected DemoMimeMessageBuilder self() {
            return this;
        }

        @Override
        protected JavaMailSender mailSender() {
            return DemoEmailSender.this.mailSender;
        }

        @Override
        protected InternetAddress sender() {
            return DemoEmailSender.this.sender;
        }

        @Override
        protected TemplateEngine templateEngine() {
            return DemoEmailSender.this.templateEngine;
        }

    }

    /**
     * Base abstract class for {@link DemoEmailSender.DemoMimeMessageBuilder}
     * 
     * @author Taylor Wong
     *
     * @param <B> type of builder instance
     */
    public abstract class AbstractDemoMimeMessageBuilder<B extends AbstractDemoMimeMessageBuilder<B>>
            extends AbstractTemplateMimeMessageBuilder<B> {

        /**
         * Constructor
         */
        public AbstractDemoMimeMessageBuilder() {
            super();
        }

        /**
         * Add/Append addresses of {@link Message.RecipientType#TO}
         * 
         * @param to addresses of {@link Message.RecipientType#TO}
         * @return
         */
        public B addTo(DemoUser... to) {
            helper = helper.andThen(message -> {
                for (DemoUser t : to) {
                    try {
                        message.addTo(EmailUtil.toInternetAddress(t));
                    } catch (MessagingException e) {
                        throw new MessagingRuntimeException("Error adding to recipient", e);
                    }
                }
            });
            return self();
        }

        /**
         * Add/Append addresses of {@link Message.RecipientType#TO}
         * 
         * @param to addresses of {@link Message.RecipientType#TO}
         * @return
         */
        public B addTo(Collection<DemoUser> to) {
            helper = helper.andThen(message -> {
                for (DemoUser t : to) {
                    try {
                        message.addTo(EmailUtil.toInternetAddress(t));
                    } catch (MessagingException e) {
                        throw new MessagingRuntimeException("Error adding to recipient", e);
                    }
                }
            });
            return self();
        }

        /**
         * Add/Append addresses of {@link Message.RecipientType#CC}
         * 
         * @param cc addresses of {@link Message.RecipientType#CC}
         * @return the builder instance
         */
        public B addCc(DemoUser... cc) {
            helper = helper.andThen(message -> {
                for (DemoUser c : cc) {
                    try {
                        message.addCc(EmailUtil.toInternetAddress(c));
                    } catch (MessagingException e) {
                        throw new MessagingRuntimeException("Error adding c recipient", e);
                    }
                }
            });
            return self();
        }

        /**
         * Add/Append addresses of {@link Message.RecipientType#CC}
         * 
         * @param cc addresses of {@link Message.RecipientType#CC}
         * @return the builder instance
         */
        public B addCc(Collection<DemoUser> cc) {
            helper = helper.andThen(message -> {
                for (DemoUser c : cc) {
                    try {
                        message.addCc(EmailUtil.toInternetAddress(c));
                    } catch (MessagingException e) {
                        throw new MessagingRuntimeException("Error adding c recipient", e);
                    }
                }
            });
            return self();
        }

        /**
         * Add/Append addresses of {@link Message.RecipientType#BCC}
         * 
         * @param bcc addresses of {@link Message.RecipientType#BCC}
         * @return the builder instance
         */
        public B addBcc(DemoUser... bcc) {
            helper = helper.andThen(message -> {
                for (DemoUser b : bcc) {
                    try {
                        message.addBcc(EmailUtil.toInternetAddress(b));
                    } catch (MessagingException e) {
                        throw new MessagingRuntimeException("Error adding bcc recipient", e);
                    }
                }
            });
            return self();
        }

        /**
         * Add/Append addresses of {@link Message.RecipientType#BCC}
         * 
         * @param bcc addresses of {@link Message.RecipientType#BCC}
         * @return the builder instance
         */
        public B addBcc(Collection<DemoUser> bcc) {
            helper = helper.andThen(message -> {
                for (DemoUser b : bcc) {
                    try {
                        message.addBcc(EmailUtil.toInternetAddress(b));
                    } catch (MessagingException e) {
                        throw new MessagingRuntimeException("Error adding bcc recipient", e);
                    }
                }
            });
            return self();
        }
    }

    /**
     * Utility class for DemoEmailSender
     * 
     * @author Taylor Wong
     *
     */
    public static class EmailUtil {

        /**
         * Convert {@link DemoUser} to {@link InternetAddress}
         * 
         * @param user
         * @return
         */
        public static InternetAddress toInternetAddress(DemoUser user) {
            String name = user.getUsername();

            if (StringUtils.isNotBlank(user.getDisplayName())) {
                name = user.getDisplayName();
            } else if (StringUtils.isNotBlank(user.getFirstName()) && StringUtils.isNotBlank(user.getLastName())) {
                name = user.getFirstName() + " " + user.getLastName();
            } else if (StringUtils.isNotBlank(user.getFirstName())) {
                name = user.getFirstName();
            } else if (StringUtils.isNotBlank(user.getLastName())) {
                name = user.getLastName();
            }

            try {
                return new InternetAddress(user.getEmail(), name);
            } catch (UnsupportedEncodingException e) {
                throw new MessagingRuntimeException(e);
            }
        }
    }
}
