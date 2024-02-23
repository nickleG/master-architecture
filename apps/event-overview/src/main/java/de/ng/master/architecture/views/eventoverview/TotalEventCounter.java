package de.ng.master.architecture.views.eventoverview;

import com.vaadin.flow.component.formlayout.FormLayout;
import java.util.ArrayList;
import java.util.List;

public class TotalEventCounter extends FormLayout {

  private List<EventWithCounter> counter = new ArrayList<>();

  public TotalEventCounter() {
    super();
    this.setResponsiveSteps(new ResponsiveStep("0", 6));
    this.addClassNames("total-event-counter", "border-small");
  }

  public void add(EventWithCounter eventWithCounter) {
    super.add(eventWithCounter);
    this.counter.add(eventWithCounter);
  }


  public void updateCounter() {
    counter.forEach(e -> e.getEventWithCounterBinder().refreshFields());
  }
}
