package de.ng.master.architecture.views.eventoverview;

import de.ng.master.architecture.eventlib.events.Event;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class EventWithCounterState<E extends Event> {

  private final String eventName;

  private Integer counter = 0;

  private final List<E> events = new ArrayList<>();


  public synchronized void increaseCounter() {
    counter++;
  }


}
