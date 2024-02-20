package de.ng.master.architecture.servicea.subscription;

import de.ng.master.architecture.eventlib.events.Notification;
import de.ng.master.architecture.eventlib.pubsub.client.SubscribeClient;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class InitSubscription implements CommandLineRunner {

  private final SubscribeClient client;

  @Override
  public void run(String... args) {
    client.subscribe(Notification.class);
  }

}
