package de.ng.master.architecture.broker.testdata;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.Serializable;
import lombok.Getter;

@Getter
public class TestTopic {

  public record TestContent(Object valueA, Object ValueB) implements Serializable {

  }

  private final ObjectMapper objectMapper = new ObjectMapper();
  String topic;

  TestContent content;

  public TestTopic() {
    this.topic = "topic";
    this.content = new TestContent(12456L, "valueB");
  }

  public String getContent() {
    try {
      return objectMapper.writeValueAsString(content);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }


}
