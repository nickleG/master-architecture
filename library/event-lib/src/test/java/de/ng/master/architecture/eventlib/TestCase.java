package de.ng.master.architecture.eventlib;

import java.net.MalformedURLException;
import java.net.URL;
import org.junit.jupiter.api.Test;

public class TestCase {

  @Test
  void test() throws MalformedURLException {
    String urlString = "http://localhost:8081/subscription";
    URL url = new URL(urlString);
    System.out.println(url.getProtocol() + "://" + url.getHost() + ":"
        + url.getPort() + "/start");
  }
}
