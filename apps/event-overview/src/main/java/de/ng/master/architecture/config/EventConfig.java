package de.ng.master.architecture.config;

import de.ng.master.architecture.eventlib.events.Alert;
import de.ng.master.architecture.eventlib.events.Event;
import de.ng.master.architecture.eventlib.events.Notification;
import de.ng.master.architecture.eventlib.events.SubscriptionEstablishedEvent;
import de.ng.master.architecture.eventlib.events.SubscriptionFailedEvent;
import java.util.List;
import lombok.Getter;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class EventConfig {

  private final List<Class<? extends Event>> events = List.of(Notification.class, Alert.class, SubscriptionEstablishedEvent.class,
      SubscriptionFailedEvent.class);
}
