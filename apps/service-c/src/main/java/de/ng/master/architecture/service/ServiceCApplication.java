package de.ng.master.architecture.service;

import de.ng.master.architecture.eventlib.config.LibConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(LibConfig.class)
public class ServiceCApplication {

  public static void main(String[] args) {
    SpringApplication.run(ServiceCApplication.class, args);
  }

}
