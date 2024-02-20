package de.ng.master.architecture.eventlib.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@ComponentScan(basePackages = "de.ng.master.architecture")
@Setter
public class LibConfig {

  @Value("${subscription.callback.url:not-set}")
  String callbackUrl;

  @Value("${subscription.publish.url:http://localhost:8083/publish/}")
  String publishUrl;

  @Value("${subscription.client.name:not-set}")
  String clientName;
}
