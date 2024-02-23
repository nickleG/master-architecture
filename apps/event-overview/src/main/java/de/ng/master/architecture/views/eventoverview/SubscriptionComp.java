package de.ng.master.architecture.views.eventoverview;

import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ReadOnlyHasValue;
import lombok.Getter;

@Getter
public class SubscriptionComp extends VerticalLayout {

  private final H4 header = new H4();
  private final Paragraph topic = new Paragraph();
  private final Paragraph counter = new Paragraph();
  private final String identifier;
  private Integer counterValue = 0;
  private final Binder<SubscriptionState> subscriptionBinder = new Binder<>();

  public SubscriptionComp(SubscriptionState subscription) {

    ReadOnlyHasValue<String> readOnlyHasValueHeader = new ReadOnlyHasValue<>(header::setText);
    ReadOnlyHasValue<String> readOnlyHasValueParagraph = new ReadOnlyHasValue<>(topic::setText);
    ReadOnlyHasValue<Integer> readOnlyHasValueCounter = new ReadOnlyHasValue<>(e -> counter.setText(e.toString()));
    subscriptionBinder.forField(readOnlyHasValueHeader).bind(SubscriptionState::getClientName, null);
    subscriptionBinder.forField(readOnlyHasValueParagraph).bind(SubscriptionState::getTopic, null);
    subscriptionBinder.forField(readOnlyHasValueCounter).bind(SubscriptionState::getCounter, null);
    add(header, topic, counter);
    subscriptionBinder.setBean(subscription);
    this.setDefaultHorizontalComponentAlignment(Alignment.START);
    this.identifier = subscription.getTopic() + subscription.getClientName();
  }

  public void increaseCounter() {
    SubscriptionState bean = subscriptionBinder.getBean();
    bean.setCounter(subscriptionBinder.getBean().getCounter() + 1);
    subscriptionBinder.setBean(bean);
  }

  public void glowBorder() {
    this.addClassNames("glow-border"); // Add the glowing effect
    // Use a Timer to remove the class after 2 seconds
  }

}
