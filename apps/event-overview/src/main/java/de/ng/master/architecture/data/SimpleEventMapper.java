package de.ng.master.architecture.data;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SimpleEventMapper {

  public static SimpleEventEntity map(SimpleEvent event) {
    SimpleEventEntity entity = new SimpleEventEntity();
    entity.setTopic(event.getTopic());
    entity.setJsonContent(event.getJsonContent());
    return entity;
  }

  public static SimpleEvent map(SimpleEventEntity entity) {
    return new SimpleEvent(entity.getTopic(), entity.getJsonContent());
  }


}
