package de.ng.master.architecture.broker.subscriber;

public class SubscriberMapper {

  public static SubscriptionEntity map(Subscription subscription) {
    SubscriptionEntity entity = new SubscriptionEntity();
    entity.setCallbackUrl(subscription.getCallbackUrl());
    entity.setTopic(subscription.getTopic());
    return entity;
  }

  public static Subscription map(SubscriptionEntity entity) {
    Subscription subscription = new Subscription();
    subscription.setCallbackUrl(entity.getCallbackUrl());
    subscription.setTopic(entity.getTopic());
    return subscription;
  }
}
