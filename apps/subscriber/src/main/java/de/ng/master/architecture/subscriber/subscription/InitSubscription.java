package de.ng.master.architecture.subscriber.subscription;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class InitSubscription implements CommandLineRunner {

  private final SubscriptionClient client;

  @Override
  public void run(String... args) {
    client.subscribe(new Subscription("http://localhost:8081/notification", Notification.class.getSimpleName()));
  }

}
