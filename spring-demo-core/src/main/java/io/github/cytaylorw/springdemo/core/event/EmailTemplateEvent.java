package io.github.cytaylorw.springdemo.core.event;

import java.util.Arrays;
import java.util.Collection;

import io.github.cytaylorw.springdemo.common.email.template.EmailTemplate;
import io.github.cytaylorw.springdemo.db.auth.entity.DemoUser;
import lombok.Data;

/**
 * EmailTemplate event
 */
@Data
public class EmailTemplateEvent {

  /**
   * template
   */
  private final EmailTemplate template;

  /**
   * to
   */
  private final Collection<DemoUser> to;

  /**
   * cc
   */
  private Collection<DemoUser> cc = Arrays.asList();

  /**
   * bcc
   */
  private Collection<DemoUser> bcc = Arrays.asList();

  /**
   * @param template
   * @param to
   * @return
   */
  public static EmailTemplateEvent of(EmailTemplate template, Collection<DemoUser> to) {
    return new EmailTemplateEvent(template, to);
  }

  /**
   * @param template
   * @param to
   * @param cc
   * @return
   */
  public static EmailTemplateEvent of(EmailTemplate template, Collection<DemoUser> to, Collection<DemoUser> cc) {
    EmailTemplateEvent event = new EmailTemplateEvent(template, to);
    event.setCc(cc);
    return event;
  }

  /**
   * @param template
   * @param to
   * @param cc
   * @param bcc
   * @return
   */
  public static EmailTemplateEvent of(EmailTemplate template, Collection<DemoUser> to, Collection<DemoUser> cc,
          Collection<DemoUser> bcc) {
    EmailTemplateEvent event = new EmailTemplateEvent(template, to);
    event.setCc(cc);
    event.setBcc(bcc);
    return event;
  }

}
