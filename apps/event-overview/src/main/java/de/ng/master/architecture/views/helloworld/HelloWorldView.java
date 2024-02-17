package de.ng.master.architecture.views.helloworld;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import de.ng.master.architecture.views.MainLayout;

@PageTitle("Hello World")
@Route(value = "hello", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class HelloWorldView extends HorizontalLayout {

  private Button sayHello;

  public HelloWorldView() {
    sayHello = new Button("Say hello");
    sayHello.addClickShortcut(Key.ENTER);

    setMargin(true);
    setVerticalComponentAlignment(Alignment.END, sayHello);


  }

}
