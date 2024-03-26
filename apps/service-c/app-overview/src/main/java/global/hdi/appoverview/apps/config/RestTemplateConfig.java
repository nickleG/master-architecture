package global.hdi.appoverview.apps.config;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
@Getter
@Setter
public class RestTemplateConfig {

  @Value("${hdi-oauth2-proxy.resource-server.proxy.host:#{null}}")
  private String proxyHost;

  @Value("${hdi-oauth2-proxy.resource-server.proxy.port:#{null}}")
  private Integer proxyPort;

  @Value("${hdi-oauth2-proxy.resource-server.proxy.disabled:#{false}}")
  private boolean proxyDisabled;

  @Bean
  @Primary
  public RestTemplate restTemplate() {
    if (proxyDisabled) {
      return new RestTemplate();
    }
    Proxy proxy = new Proxy(Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
    SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
    requestFactory.setProxy(proxy);
    return new RestTemplate(requestFactory);
  }

  @Bean
  @Qualifier("withoutProxy")
  public RestTemplate restTemplateWithoutProxy() {
    return new RestTemplate();
  }

}
