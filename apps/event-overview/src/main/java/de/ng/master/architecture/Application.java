package de.ng.master.architecture;

import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.theme.Theme;
import de.ng.master.architecture.data.SamplePersonRepository;
import javax.sql.DataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.sql.init.SqlDataSourceScriptDatabaseInitializer;
import org.springframework.boot.autoconfigure.sql.init.SqlInitializationProperties;
import org.springframework.context.annotation.Bean;

/**
 * The entry point of the Spring Boot application.
 * <p>
 * Use the @PWA annotation make the application installable on phones, tablets and some desktop browsers.
 */
@SpringBootApplication
@NpmPackage(value = "@fontsource/inconsolata", version = "4.5.0")
@Theme(value = "event-overview")
@Push
public class Application implements AppShellConfigurator {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  SqlDataSourceScriptDatabaseInitializer dataSourceScriptDatabaseInitializer(DataSource dataSource,
      SqlInitializationProperties properties, SamplePersonRepository repository) {
    // This bean ensures the database is only initialized when empty
    return new SqlDataSourceScriptDatabaseInitializer(dataSource, properties) {
      @Override
      public boolean initializeDatabase() {
        if (repository.count() == 0L) {
          return super.initializeDatabase();
        }
        return false;
      }
    };
  }
}
