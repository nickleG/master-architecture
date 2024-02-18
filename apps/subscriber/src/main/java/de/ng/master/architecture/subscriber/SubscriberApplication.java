package de.ng.master.architecture.subscriber;

import de.ng.master.architecture.eventlib.config.LibConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(LibConfig.class)
public class SubscriberApplication {

  public static void main(String[] args) {
    SpringApplication.run(SubscriberApplication.class, args);
  }

}
