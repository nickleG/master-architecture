package global.hdi.appoverview.apps;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AppOverviewController {
  private final AppTestDataGenerator appTestDataGenerator = new AppTestDataGenerator();
  private final RestTemplate restTemplate;
  List<AppStateDTO> apps = List.of(AppStateDTO.builder()
          .appName("Ecert Backend")
          .appVersion("1.0.0")
          .appHealth("UP")
          .grafanaURL("https://grafana.global-apps-1.gwc.azure.ew.zz")
          .argoURL("https://argocd.global-apps-1.gwc.azure.ew.zz/applications/argocd/acc004-ecert-ecert-backend")
          .lastDeployed(null)

      .build());
  @GetMapping(value = "/app-overview", produces = "application/json")
  public ResponseEntity<List<AppStateDTO>> getAppOverview(){
    log.info("Getting app overview");
    var rest = appTestDataGenerator.generate();
    rest.forEach(System.out::println);
    var retu = restTemplate.getForEntity("https://ecert.global-apps-1.gwc.azure.ew.zz/actuator/health/liveness", String.class);
    System.out.println("STATUS: " + retu);
    System.out.println("STATUS: " + retu.getStatusCode());
    System.out.println("STATUS: " + retu.getBody());
    System.out.println("STATUS: " + retu.getHeaders());

    return ResponseEntity.ok(apps);
  }
}
