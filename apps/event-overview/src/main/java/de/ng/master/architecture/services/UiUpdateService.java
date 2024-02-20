package de.ng.master.architecture.services;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.Command;
import com.vaadin.flow.server.UIInitEvent;
import com.vaadin.flow.server.UIInitListener;
import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;
import lombok.extern.slf4j.Slf4j;
import org.atmosphere.config.service.Singleton;
import org.springframework.stereotype.Service;

@Service
@Singleton
@Slf4j
public class UiUpdateService implements UIInitListener {

  private final Set<UI> uis = Collections.newSetFromMap(new WeakHashMap<>());

  public void updateAllUi(Command command) {
    uis.forEach(ui -> ui.access(command));
  }

  @Override
  public void uiInit(UIInitEvent uiInitEvent) {
    uis.add(uiInitEvent.getUI());
    uiInitEvent.getUI().addDetachListener(e -> this.uis.remove(e.getUI()));
    log.info("UI initialized: " + uis.size() + " uis active");
  }
}