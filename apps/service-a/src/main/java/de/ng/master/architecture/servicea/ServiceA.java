package de.ng.master.architecture.servicea;

import de.ng.master.architecture.eventlib.config.LibConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(LibConfig.class)
public class ServiceA {

  public static void main(String[] args) {
    SpringApplication.run(ServiceA.class, args);
  }

}
