package de.ng.master.architecture.views.eventoverview;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import de.ng.master.architecture.eventlib.pubsub.client.Subscription;
import de.ng.master.architecture.views.MainLayout;
import java.util.List;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@PageTitle("Hello World")
@Route(value = "", layout = MainLayout.class)
@Slf4j
@Getter
@PreserveOnRefresh
public class EventOverview extends VerticalLayout {

  private final FormLayout subscriptionLayout = new FormLayout();
  private final EventOverviewService overviewService;
  private final FlowControlClient client;
  private final TotalEventCounter totalEventCounter = new TotalEventCounter();

  public EventOverview(EventOverviewService overviewService, FlowControlClient flowControlClient) {
    this.overviewService = overviewService;
    this.client = flowControlClient;
    this.add(totalEventCounter);
    FormLayout commandBar = new FormLayout();
    commandBar.setResponsiveSteps(new ResponsiveStep("0", 6));
    ComboBox<Subscription> subscriptionComboBox = new ComboBox<>("Service");
    subscriptionComboBox.setItems(overviewService.getSubscriptions());
    subscriptionComboBox.setValue(overviewService.getSubscriptions().getFirst());
    commandBar.add(subscriptionComboBox);
    TextField commandValue = new TextField("Command Value", "alert");

    subscriptionComboBox.setItemLabelGenerator(Subscription::getClientName);
    ComboBox<Action> actions = new ComboBox<>("Action");
    actions.setItems(overviewService.getActions(client, subscriptionComboBox, commandValue));
    actions.setItemLabelGenerator(Action::name);
    actions.addValueChangeListener(e -> {
      commandValue.setVisible(!List.of("Start", "Stop").contains(e.getValue().name()));
      if (e.getValue().name().equals("TimeOut")) {
        commandValue.setValue("3000");
      } else {
        commandValue.setValue("alert");
      }

    });

    actions.setValue(overviewService.getActions(client, subscriptionComboBox, commandValue).getFirst());
    Button button = new Button("Execute", e -> actions.getValue().action().run());
    button.addClickShortcut(Key.ENTER);
    button.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_CONTRAST);
    commandBar.add(actions, commandValue, button);
    add(commandBar);

    setWidth("100%");
    setHeight("100%");
    setAlignItems(Alignment.START);

    subscriptionLayout.setResponsiveSteps(new ResponsiveStep("0", 6));
    this.add(subscriptionLayout);

  }


  @Override
  public void onAttach(AttachEvent attachEvent) {
    super.onAttach(attachEvent);
    log.info("onAttach: {}, initial: {}", attachEvent.getSource(), attachEvent.isInitialAttach());
    overviewService.add(this);
    if (!attachEvent.isInitialAttach()) {
      overviewService.replaySubscriptions(this);

    }
  }

  @Override
  public void onDetach(DetachEvent detachEvent) {
    super.onDetach(detachEvent);
    overviewService.remove(this);
  }

  public SubscriptionComp addSubscription(SubscriptionState subscription) {
    SubscriptionComp subscriptionComp = new SubscriptionComp(subscription);
    subscriptionLayout.add(subscriptionComp);
    return subscriptionComp;
  }

  public void addEventState(EventWithCounter eventWithCounterState) {
    this.totalEventCounter.add(eventWithCounterState);
  }
}
