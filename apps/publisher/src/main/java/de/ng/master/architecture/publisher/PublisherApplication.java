package de.ng.master.architecture.publisher;

import de.ng.master.architecture.eventlib.config.LibConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(LibConfig.class)
public class PublisherApplication {

  public static void main(String[] args) {
    SpringApplication.run(PublisherApplication.class, args);
  }

}
