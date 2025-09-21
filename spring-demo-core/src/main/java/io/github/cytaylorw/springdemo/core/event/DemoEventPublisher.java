package io.github.cytaylorw.springdemo.core.event;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import io.github.cytaylorw.springdemo.common.email.template.EmailTemplate;
import io.github.cytaylorw.springdemo.db.auth.entity.DemoUser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DemoEventPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void emailTemplateEvent(final EmailTemplate template, final Collection<DemoUser> to) {
        log.debug("Publishing EmailTemplateEvent. ");
        EmailTemplateEvent customSpringEvent = EmailTemplateEvent.of(template, to);
        applicationEventPublisher.publishEvent(customSpringEvent);
    }

    public void emailTemplateEvent(final EmailTemplate template, final Collection<DemoUser> to,
            final Collection<DemoUser> cc) {
        log.debug("Publishing EmailTemplateEvent. ");
        EmailTemplateEvent customSpringEvent = EmailTemplateEvent.of(template, to, cc);
        applicationEventPublisher.publishEvent(customSpringEvent);
    }

    public void emailTemplateEvent(final EmailTemplate template, final Collection<DemoUser> to,
            final Collection<DemoUser> cc, final Collection<DemoUser> bcc) {
        log.debug("Publishing EmailTemplateEvent. ");
        EmailTemplateEvent customSpringEvent = EmailTemplateEvent.of(template, to, bcc);
        applicationEventPublisher.publishEvent(customSpringEvent);
    }
}
