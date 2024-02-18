package de.ng.master.architecture.eventlib.events;

import java.io.Serializable;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Event implements Serializable {

  private final UUID uuid = UUID.randomUUID();
}
