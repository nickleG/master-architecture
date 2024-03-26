package de.ng.master.architecture.views.about;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import de.ng.master.architecture.views.MainLayout;

@PageTitle("About")
@Route(value = "about", layout = MainLayout.class)
public class AboutView extends VerticalLayout {

  public AboutView() {
    setSpacing(false);

    Image img = new Image("images/img.png", "Architektur");
    img.setWidth("600px");
    add(img);

    H2 header = new H2("Architektur");
    header.addClassNames(Margin.Top.XLARGE, Margin.Bottom.MEDIUM);
    add(header);

    setSizeFull();
    setJustifyContentMode(JustifyContentMode.CENTER);
    setDefaultHorizontalComponentAlignment(Alignment.CENTER);
    getStyle().set("text-align", "center");
  }

}
