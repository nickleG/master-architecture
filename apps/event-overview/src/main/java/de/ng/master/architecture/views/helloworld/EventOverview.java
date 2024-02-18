package de.ng.master.architecture.views.helloworld;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import de.ng.master.architecture.views.MainLayout;
import lombok.Getter;

@PageTitle("Hello World")
@Route(value = "hello", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@Getter
public class EventOverview extends HorizontalLayout {

  private final Button sayHello;

  public EventOverview() {
    sayHello = new Button("Say hello");
    sayHello.addClickShortcut(Key.ENTER);

    setMargin(true);
    setVerticalComponentAlignment(Alignment.END, sayHello);


  }

}
