package de.ng.master.architecture.broker.publisher;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PubServiceTest {

  @Mock
  SimpleEventRepository simpleEventRepository;

  @InjectMocks
  PubService pubService;

  @Test
  void handleEvent() {
    SimpleEvent event = new SimpleEvent("test", "test");
    pubService.handleEvent(event);
    verify(simpleEventRepository).save(SimpleEventMapper.map(event));
  }
}