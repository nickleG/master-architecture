package de.ng.master.architecture.broker.subscriber;

public class SubscriberMapper {

  public static SubscriberEntity map(Subscriber subscriber) {
    SubscriberEntity entity = new SubscriberEntity();
    entity.setCallbackUrl(subscriber.getCallbackUrl());
    entity.setTopic(subscriber.getTopic());
    return entity;
  }

  public static Subscriber map(SubscriberEntity entity) {
    Subscriber subscriber = new Subscriber();
    subscriber.setCallbackUrl(entity.getCallbackUrl());
    subscriber.setTopic(entity.getTopic());
    return subscriber;
  }
}
