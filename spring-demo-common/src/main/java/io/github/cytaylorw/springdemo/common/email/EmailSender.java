package io.github.cytaylorw.springdemo.common.email;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Consumer;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

/**
 * E-mail sender class with builder.
 * 
 * @author Taylor Wong
 *
 */
@RequiredArgsConstructor
public class EmailSender {

    /**
     * JavaMailSender
     */
    protected final JavaMailSender mailSender;

    /**
     * Sender address
     */
    protected final InternetAddress sender;

    /**
     * Initialize the {@link MimeMessageBuilder} instance
     * 
     * @return {@link MimeMessageBuilder} instance
     */
    public MimeMessageBuilder builder() {
        return this.new MimeMessageBuilder();
    }

    /**
     * Send messages
     * 
     * @param mimeMessages
     */
    public void sendMessage(MimeMessage... mimeMessages) {
        this.mailSender.send(mimeMessages);
    }

    /**
     * Create a text message
     * 
     * @param subject e-mail subject
     * @param text    text content
     * @param to      TO recipients
     * @return MIME style e-mail message
     */
    public MimeMessage createMessage(String subject, String text, String... to) {
        return this.builder().addTo(to).setSubject(subject).setText(text).build();
    }

    /**
     * Create and send a text message
     * 
     * @param subject e-mail subject
     * @param text    text content
     * @param to      TO recipients
     */
    public void sendMessage(String subject, String text, String... to) {
        MimeMessage message = this.createMessage(subject, text, to);
        this.mailSender.send(message);
    }

    /**
     * The builder class for {@link EmailSender}
     * 
     * @author Taylor Wong
     *
     */
    public class MimeMessageBuilder extends AbstractMimeMessageBuilder<MimeMessageBuilder> {

        @Override
        protected MimeMessageBuilder self() {
            return this;
        }

        @Override
        protected JavaMailSender mailSender() {
            return EmailSender.this.mailSender;
        }

        @Override
        protected InternetAddress sender() {
            return EmailSender.this.sender;
        }

    }

    /**
     * Base abstract class for {@link EmailSender.MimeMessageBuilder}
     * 
     * @author Taylor Wong
     *
     * @param <B> type of builder instance
     */
    public abstract class AbstractMimeMessageBuilder<B extends AbstractMimeMessageBuilder<B>> {

        /**
         * Default attachment content type
         */
        private static final String DEAULT_CONTENT_TYPE = "application/octet-stream";

        /**
         * Flag to track attachments, inline elements.
         */
        private boolean isMultipart = false;

        /**
         * {@link MimeMessageHelper} consumer for building message.
         */
        protected Consumer<MimeMessageHelper> helper = message -> {
        };

        /**
         * Return the current class instance.
         * 
         * @return the current class instance
         */
        protected abstract B self();

        /**
         * Return the {@link JavaMailSender} instance
         * 
         * @return {@link JavaMailSender} instance
         */
        protected abstract JavaMailSender mailSender();

        /**
         * Return the default {@link InternetAddress} sender
         * 
         * @return default {@link InternetAddress} sender
         */
        protected abstract InternetAddress sender();

        /**
         * Constructor
         */
        public AbstractMimeMessageBuilder() {
            this.setFrom(this.sender());
        }

        /**
         * The default multipart mode
         * 
         * @return multipart mode
         */
        protected int getMultipartMode() {
            return MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED;
        }

        /**
         * Set sender address
         * 
         * @param from sender address
         * @return the builder instance
         */
        public B setFrom(String from) {
            helper = helper.andThen(message -> {
                try {
                    message.setFrom(from);
                } catch (MessagingException e) {
                    throw new MessagingRuntimeException("Error setting sender", e);
                }
            });
            return self();
        }

        /**
         * Set sender address
         * 
         * @param from sender address
         * @return the builder instance
         */
        public B setFrom(String from, String personal) {
            helper = helper.andThen(message -> {
                try {
                    message.setFrom(from, personal);
                } catch (MessagingException | UnsupportedEncodingException e) {
                    throw new MessagingRuntimeException("Error setting sender", e);
                }
            });
            return self();
        }

        /**
         * Set sender address
         * 
         * @param from sender address
         * @return the builder instance
         */
        public B setFrom(InternetAddress from) {
            helper = helper.andThen(message -> {
                try {
                    message.setFrom(from);
                } catch (MessagingException e) {
                    throw new MessagingRuntimeException("Error setting sender", e);
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
        public B addTo(InternetAddress... to) {
            helper = helper.andThen(message -> {
                for (InternetAddress t : to) {
                    try {
                        message.addTo(t);
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
        public B addTo(String... to) {
            helper = helper.andThen(message -> {
                for (String t : to) {
                    try {
                        message.addTo(t);
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
        public B addCc(InternetAddress... cc) {
            helper = helper.andThen(message -> {
                for (InternetAddress c : cc) {
                    try {
                        message.addCc(c);
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
        public B addCc(String... cc) {
            helper = helper.andThen(message -> {
                for (String c : cc) {
                    try {
                        message.addCc(c);
                    } catch (MessagingException e) {
                        throw new MessagingRuntimeException("Error adding cc recipient", e);
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
        public B addBcc(InternetAddress... bcc) {
            helper = helper.andThen(message -> {
                for (InternetAddress b : bcc) {
                    try {
                        message.addBcc(b);
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
        public B addBcc(String... bcc) {
            helper = helper.andThen(message -> {
                for (String b : bcc) {
                    try {
                        message.addBcc(b);
                    } catch (MessagingException e) {
                        throw new MessagingRuntimeException("Error adding bcc recipient", e);
                    }
                }
            });
            return self();
        }

        /**
         * Set E-mail subject
         * 
         * @param subject
         * @return the builder instance
         */
        public B setSubject(String subject) {
            helper = helper.andThen(message -> {
                try {
                    message.setSubject(subject);
                } catch (MessagingException e) {
                    throw new MessagingRuntimeException(e);
                }
            });
            return self();
        }

        /**
         * Set E-mail content
         * 
         * @param text E-mail content
         * @param html content type
         * @return
         */
        public B setText(String text, boolean html) {
            helper = helper.andThen(message -> {
                try {
                    message.setText(text, html);
                } catch (MessagingException e) {
                    throw new MessagingRuntimeException(e);
                }
            });
            return self();
        }

        /**
         * Set E-mail text content
         * 
         * @param text E-mail text content
         * @return
         */
        public B setText(String text) {
            return this.setText(text, false);
        }

        /**
         * Add an inline element
         * 
         * @param contentId inline element ID
         * @param file      the file to be attached
         * @return
         */
        public B addInline(String contentId, File file) {
            helper = helper.andThen(message -> {
                try {
                    message.addInline(contentId, file);
                    this.isMultipart = true;
                } catch (MessagingException e) {
                    throw new MessagingRuntimeException(e);
                }
            });
            return self();
        }

        /**
         * Add file attachment
         * 
         * @param file       the file to be attached
         * @param contenType file content type
         * @return the builder instance
         */
        public B addAttachment(File file, String contenType) {
            helper = helper.andThen(message -> {
                try {
                    message.addAttachment(file.getName(), new FileSystemResource(file), contenType);
                    this.isMultipart = true;
                } catch (MessagingException e) {
                    throw new MessagingRuntimeException(e);
                }
            });
            return self();
        }

        /**
         * Add file attachment as default content type
         * {@link AbstractMimeMessageBuilder#DEAULT_CONTENT_TYPE}
         * 
         * @param file the file to be attached
         * @return the builder instance
         */
        public B addAttachment(File file) {
            return this.addAttachment(file, DEAULT_CONTENT_TYPE);
        }

        /**
         * Add files attachment as default content type
         * {@link AbstractMimeMessageBuilder#DEAULT_CONTENT_TYPE}
         * 
         * @param files files to be attached
         * @return the builder instance
         */
        public B addAttachment(Collection<File> files) {
            if (Objects.nonNull(files)) {
                files.forEach(f -> this.addAttachment(f));
            }

            return self();
        }

        /**
         * Create the MIME style e-mail message from the builder with specified
         * multipart mode if there is any inlines or attachments.
         * 
         * @param multipartMode See {@link MimeMessageHelper }
         * @return MIME style e-mail message
         */
        public MimeMessage build(int multipartMode) {
            final MimeMessage mimeMessage = this.mailSender().createMimeMessage();
            MimeMessageHelper message = null;
            int mode = this.isMultipart ? multipartMode : MimeMessageHelper.MULTIPART_MODE_NO;
            try {
                message = new MimeMessageHelper(mimeMessage, mode, "UTF-8");
            } catch (MessagingException e) {
                throw new MessagingRuntimeException(e);
            }
            helper.accept(message);

            return mimeMessage;
        }

        /**
         * Create the MIME style e-mail message from the builder with default multipart
         * mode if there is any inlines or attachments.
         * 
         * @return MIME style e-mail message
         */
        public MimeMessage build() {
            return this.build(this.getMultipartMode());
        }

        /**
         * Create and send the MIME style e-mail message from the builder.
         */
        public void buildAndSend() {
            this.mailSender().send(this.build());
        }
    }
}
