package de.ng.master.architecture.eventlib.util;

import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WaitTime {

  /**
   * Waits a random time between 100 and 900 ms.
   */
  public void waitGaussTime() {
    try {
      double v = 500 + 400 * new Random().nextGaussian();
      if (v <= 0) {
        return;
      }
      Thread.sleep((long) v);
    } catch (InterruptedException e) {
      log.info(e.getMessage());
    }
  }

  /**
   * Waits a random time plus minus deviation around mean.
   */
  public void waitGaussTime(long mean, long deviation) {
    try {
      double v = mean + deviation * new Random().nextGaussian();
      Thread.sleep((long) v);
    } catch (InterruptedException e) {
      log.info(e.getMessage());
    }
  }
}
