package io.github.cytaylorw.springdemo.core.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import io.github.cytaylorw.springdemo.core.email.DemoEmailSender;
import lombok.extern.slf4j.Slf4j;

/**
 * EmailEventListener
 * 
 * @author Taylor Wong
 *
 */
@Slf4j
@Component
public class EmailEventListener {

  @Autowired
  private DemoEmailSender emailSender;

  /**
   * @param event
   */
  @EventListener
  public void handleEmailTemplateEvent(EmailTemplateEvent event) {
    log.info("EmailTemplateEvent: {}", event);
    this.emailSender.sendMessage(event.getTemplate(), event.getTo(), event.getCc(), event.getBcc());
  }
}
