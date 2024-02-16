package de.ng.master.architecture.broker.subscriber;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest({SubscriptionController.class})
@AutoConfigureWebClient
@AutoConfigureMockMvc
class SubscriptionControllerTest {

  @Autowired
  MockMvc mockMvc;
  @MockBean
  SubscriptionService subscriptionService;
  ObjectMapper mapper = new ObjectMapper();

  @Test
  void subscribe() throws Exception {
    String subscriberContent = mapper.writeValueAsString(
        new Subscription("test", "test"));
    System.out.printf(subscriberContent);
    mockMvc.perform(MockMvcRequestBuilders.post("/subscribe").content(subscriberContent)
        .contentType("application/json")).andExpect(status().isOk());

  }

}