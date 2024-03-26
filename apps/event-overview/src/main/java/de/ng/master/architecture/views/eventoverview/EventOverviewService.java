package de.ng.master.architecture.views.eventoverview;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.textfield.TextField;
import de.ng.master.architecture.eventlib.events.Event;
import de.ng.master.architecture.eventlib.events.SubscriptionEstablishedEvent;
import de.ng.master.architecture.eventlib.events.SubscriptionRemovedEvent;
import de.ng.master.architecture.eventlib.pubsub.client.Subscription;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;


@RequiredArgsConstructor
@Getter
@Setter
@Slf4j
public class EventOverviewService {

  private final Map<EventOverview, Map<SubscriptionState, SubscriptionComp>> eventOverviewSubscriptions = new HashMap<>();

  private final Map<EventOverview, Map<Class<? extends Event>, EventWithCounterState<?>>> eventOverviewEvents = new HashMap<>();

  public void addSubscription(SubscriptionEstablishedEvent event) {
    log.info("Adding subscription: {}", event);
    eachSubscriptionOfNotAlreadyAdded(event.getClientName(), event.getTopic()).forEach(entry -> {
          log.info("Adding subscription to: {}", entry.getKey());
          SubscriptionState subscriptionState = new SubscriptionState(event.getClientName(), event.getTopic());
          SubscriptionComp subscriptionComp = entry.getKey().addSubscription(subscriptionState);
          entry.getValue().put(subscriptionState, subscriptionComp);
        }
    );
  }

  public void removeSubscription(SubscriptionRemovedEvent event) {
    log.info("remove subscription: {}", event);
    eachSubscriptionOf(event.getClientName(), event.getTopic()).forEach(entry -> {
          log.info("remove subscription to: {}", entry.getKey());
          entry.getValue().keySet().stream().filter(e -> e.getClientName().equals(event.getClientName()) && e.getTopic().equals(event.getTopic())).forEach(state
              -> {
            log.info("remove subscription to: {}", state);
            SubscriptionComp comp = entry.getValue().remove(state);
            entry.getKey().removeSubscription(comp);
          });
//          SubscriptionComp subscriptionComp = entry.getKey().removeSubscription(subscriptionState);
          //        entry.getValue().put(subscriptionState, subscriptionComp);
        }
    );
  }

  private Stream<Entry<EventOverview, Map<SubscriptionState, SubscriptionComp>>> eachSubscriptionOfNotAlreadyAdded(String eventClientName, String eventTopic) {
    return eventOverviewSubscriptions.entrySet().stream().filter(e ->
        e.getValue().keySet().stream().noneMatch(subscriptionState ->
            subscriptionState.getClientName().equals(eventClientName) &&
                subscriptionState.getTopic().equals(eventTopic)
        ));
  }

  private Stream<Entry<EventOverview, Map<SubscriptionState, SubscriptionComp>>> eachSubscriptionOf(String eventClientName, String eventTopic) {
    return eventOverviewSubscriptions.entrySet().stream().filter(e ->
        e.getValue().keySet().stream().anyMatch(subscriptionState ->
            subscriptionState.getClientName().equals(eventClientName) &&
                subscriptionState.getTopic().equals(eventTopic)
        ));
  }

  private Stream<SubscriptionComp> subscriptionComponentsFor(String eventClientName, String eventTopic) {
    return eventOverviewSubscriptions.values().stream()
        .flatMap(e -> e.values().stream())
        .filter(e -> {
          SubscriptionState bean = e.getSubscriptionBinder().getBean();
          return bean.getClientName().equals(eventClientName) &&
              bean.getTopic().equals(eventTopic);
        });
  }


  public void onEventArrived(String eventClientName, String eventTopic) {
    subscriptionComponentsFor(eventClientName, eventTopic).forEach(e -> {
      e.increaseCounter();
      e.glowBorder();
    });
  }

  public List<Subscription> getSubscriptions() {
    return List.of(new Subscription("http://localhost:8081", null, "Service A"),
        new Subscription("http://localhost:8082", null, "Service B"),
        new Subscription("http://localhost:8084", null, "Service C"));
  }

  public List<Action> getActions(FlowControlClient client, ComboBox<Subscription> subscriptionComboBox, TextField commandValue,
      ComboBox<String> commandBombobox) {
    return List.of(
        new Action("Start", () -> client.start(subscriptionComboBox.getValue())),
        new Action("Stop", () -> client.stop(subscriptionComboBox.getValue())),
        new Action("TimeOut", () -> client.timeout(subscriptionComboBox.getValue(), commandValue.getValue())),
        new Action("Publish", () -> client.publish(subscriptionComboBox.getValue(), commandBombobox.getValue())),
        new Action("Subscribe", () -> client.subscribe(subscriptionComboBox.getValue(), commandBombobox.getValue())),
        new Action("Unsubscribe", () -> client.unsubscribe(subscriptionComboBox.getValue(), commandBombobox.getValue())));
  }

  public void add(EventOverview eventOverview) {
    this.eventOverviewSubscriptions.put(eventOverview, new HashMap<>());
    this.eventOverviewEvents.put(eventOverview, new HashMap<>());
  }

  public void remove(EventOverview eventOverview) {
    this.eventOverviewSubscriptions.remove(eventOverview);
  }

  public void replaySubscriptions(EventOverview eventOverview) {
    eventOverviewSubscriptions.entrySet().stream().findAny().ifPresent(e -> e.getValue().keySet().forEach(eventOverview::addSubscription));
  }

  public void increaseEventCounter(Event message) {
    eventOverviewEvents.entrySet().forEach(e -> {
          Map<Class<? extends Event>, EventWithCounterState<?>> eventStateMap = e.getValue();
          EventWithCounterState<?> eventWithCounterState = eventStateMap.get(message.getClass());
          if (eventWithCounterState == null) {
            eventWithCounterState = new EventWithCounterState<>(message.getClass().getSimpleName());
            e.getKey().addEventState(new EventWithCounter(eventWithCounterState));
          }
          eventWithCounterState.increaseCounter();
          eventStateMap.put(message.getClass(), eventWithCounterState);
          e.getKey().getTotalEventCounter().updateCounter();
        }
    );


  }

}
