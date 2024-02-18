package de.ng.master.architecture.init;

import de.ng.master.architecture.config.EventConfig;
import de.ng.master.architecture.eventlib.pubsub.client.SubscribeClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OnStartup implements CommandLineRunner {

  private final EventConfig eventConfig;
  private final SubscribeClient subscribeClient;

  @Override
  public void run(String... args) {
    log.info("Subscribing to events");
    eventConfig.getEvents().forEach(subscribeClient::subscribe);
  }
}
