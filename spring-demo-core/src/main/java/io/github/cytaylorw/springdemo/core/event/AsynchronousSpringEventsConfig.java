package io.github.cytaylorw.springdemo.core.event;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

/**
 * Spring Events 非同步設定
 */
@Configuration
//@Profile("prod")
public class AsynchronousSpringEventsConfig {

  /**
   * @return
   */
  @Bean(name = "applicationEventMulticaster")
  public ApplicationEventMulticaster simpleApplicationEventMulticaster() {
    SimpleApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();

    eventMulticaster.setTaskExecutor(new SimpleAsyncTaskExecutor());
    return eventMulticaster;
  }

}
