package de.ng.master.architecture.views.gridwithfilters;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import com.vaadin.flow.theme.lumo.LumoUtility;
import de.ng.master.architecture.data.SimpleEventEntity;
import de.ng.master.architecture.eventlib.events.Alert;
import de.ng.master.architecture.eventlib.events.Notification;
import de.ng.master.architecture.eventlib.events.SubscriptionEstablishedEvent;
import de.ng.master.architecture.eventlib.events.SubscriptionFailedEvent;
import de.ng.master.architecture.eventlib.events.SubscriptionRemoveFailEvent;
import de.ng.master.architecture.eventlib.events.SubscriptionRemovedEvent;
import de.ng.master.architecture.eventlib.events.SuccessfulArrivedEvent;
import de.ng.master.architecture.services.SimpleEventService;
import de.ng.master.architecture.views.MainLayout;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

@PageTitle("Event Overview Grid")
@Route(value = "grid-with-filters", layout = MainLayout.class)
@Uses(Icon.class)
@Slf4j
public class GridwithFiltersView extends Div {

  private Grid<SimpleEventEntity> grid;

  private final SimpleEventService simpleEventService;

  public GridwithFiltersView(SimpleEventService SimpleEventService) {
    this.simpleEventService = SimpleEventService;
    setSizeFull();
    addClassNames("gridwith-filters-view");

    Filters filters = new Filters(this::refreshGrid);
    VerticalLayout layout = new VerticalLayout(
        filters,
        createGrid());
    layout.setSizeFull();
    layout.setPadding(false);
    layout.setSpacing(false);
    add(layout);
  }


  public static class Filters extends Div implements Specification<SimpleEventEntity> {

    private final TextField jsonContent = new TextField("Json Content");
    private final TextField published = new TextField("Published");
    private final ComboBox<String> topic = new ComboBox<>("topic");

    public Filters(Runnable onSearch) {
      log.info("search started");
      setWidthFull();
      addClassName("filter-layout");
      addClassNames(LumoUtility.Padding.Horizontal.LARGE, LumoUtility.Padding.Vertical.MEDIUM,
          LumoUtility.BoxSizing.BORDER);
      jsonContent.setPlaceholder("JsonContent");

      topic.setItems(Stream.of(Alert.class,
          Notification.class,
          SubscriptionEstablishedEvent.class,
          SubscriptionFailedEvent.class,
          SubscriptionRemovedEvent.class,
          SubscriptionRemoveFailEvent.class,
          SuccessfulArrivedEvent.class).map(Class::getSimpleName).toList());
      topic.setPlaceholder("Topic");
      topic.setClearButtonVisible(true);

      // Action buttons
      Button resetBtn = new Button("Reset");
      resetBtn.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
      resetBtn.addClickListener(e -> {
        jsonContent.clear();
        topic.clear();
        published.clear();
        onSearch.run();
      });
      Button searchBtn = new Button("Search");
      searchBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
      searchBtn.addClickListener(e -> onSearch.run());

      Div actions = new Div(resetBtn, searchBtn);
      actions.addClassName(LumoUtility.Gap.SMALL);
      actions.addClassName("actions");

      add(jsonContent, topic, published, actions);
    }


    @Override
    public Predicate toPredicate(Root<SimpleEventEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
      List<Predicate> predicates = new ArrayList<>();

      if (!jsonContent.isEmpty()) {
        String lowerCaseFilter = jsonContent.getValue().toLowerCase();
        Predicate firstNameMatch = criteriaBuilder.like(criteriaBuilder.lower(root.get("jsonContent")),
            lowerCaseFilter + "%");
        predicates.add(criteriaBuilder.or(firstNameMatch));
      }
      if (published.getValue() != null) {
        String databaseColumn = "published";
        predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(databaseColumn),
            criteriaBuilder.literal(published.getValue())));
      }
      if (topic.getValue() != null) {
        String databaseColumn = "topic";
        predicates.add(criteriaBuilder.greaterThanOrEqualTo(criteriaBuilder.literal(topic.getValue()),
            root.get(databaseColumn)));
      }

      return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }

    private String ignoreCharacters(String characters, String in) {
      String result = in;
      for (int i = 0; i < characters.length(); i++) {
        result = result.replace("" + characters.charAt(i), "");
      }
      return result;
    }

    private Expression<String> ignoreCharacters(String characters, CriteriaBuilder criteriaBuilder,
        Expression<String> inExpression) {
      Expression<String> expression = inExpression;
      for (int i = 0; i < characters.length(); i++) {
        expression = criteriaBuilder.function("replace", String.class, expression,
            criteriaBuilder.literal(characters.charAt(i)), criteriaBuilder.literal(""));
      }
      return expression;
    }


  }

  private Component createGrid() {
    grid = new Grid<>(SimpleEventEntity.class, true);
    grid.getColumns().forEach(column -> column.setAutoWidth(true));

    grid.setItems(query -> simpleEventService.list(
        PageRequest.of(query.getPage(), query.getPageSize(), VaadinSpringDataHelpers.toSpringDataSort(query))).stream());
    grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
    grid.addClassNames(LumoUtility.Border.TOP, LumoUtility.BorderColor.CONTRAST_10);

    return grid;
  }

  private void refreshGrid() {
    grid.getDataProvider().refreshAll();
  }

}
