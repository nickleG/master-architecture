package de.ng.master.architecture.broker.publisher;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest({PubController.class})
@AutoConfigureWebClient
@AutoConfigureMockMvc
class PubControllerTest {

  @Autowired
  MockMvc mockMvc;
  @InjectMocks
  PubController pubController;
  @MockBean
  PubService pubService;
  ObjectMapper mapper = new ObjectMapper();

  @Test
  void publishSimpleEvent() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/publish").content(mapper.writeValueAsString(new SimpleEvent("test", "test")))
        .contentType("application/json")).andExpect(status().isOk());
    verify(pubService).handleEvent(new SimpleEvent("test", "test"));
  }

  @Test
  void shouldNotAcceptEmptyEvent() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/publish").content(mapper.writeValueAsString(new SimpleEvent("", "")))
        .contentType("application/json")).andExpect(status().isBadRequest());
    verifyNoInteractions(pubService);
  }
}