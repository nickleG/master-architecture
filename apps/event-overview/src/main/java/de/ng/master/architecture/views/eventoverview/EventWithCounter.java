package de.ng.master.architecture.views.eventoverview;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ReadOnlyHasValue;
import lombok.Getter;

@Getter
public class EventWithCounter extends VerticalLayout {

  private final Span eventName = new Span();
  private final H2 counter = new H2();
  private final Binder<EventWithCounterState<?>> eventWithCounterBinder = new Binder<>();

  public EventWithCounter(EventWithCounterState<?> eventWithCounterState) {
    super();
    this.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
    ReadOnlyHasValue<String> readOnlyHasValue = new ReadOnlyHasValue<>(eventName::setText);
    ReadOnlyHasValue<Integer> readOnlyHasValueCounter = new ReadOnlyHasValue<>(e -> counter.setText(e.toString()));
    eventWithCounterBinder.forField(readOnlyHasValue).bind(EventWithCounterState::getEventName, null);
    eventWithCounterBinder.forField(readOnlyHasValueCounter).bind(EventWithCounterState::getCounter, null);
    eventWithCounterBinder.setBean(eventWithCounterState);

    this.add(eventName, counter);
  }
}
