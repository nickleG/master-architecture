package global.hdi.appoverview.apps;

import java.time.LocalDate;
import java.util.List;

public class AppTestDataGenerator {

  public List<AppStateDTO> generate(){
    return List.of(
      AppStateDTO.builder()
        .appName("app1")
        .appVersion("1.0.0")
        .appHealth("UP")
        .grafanaURL("http://grafana")
        .argoURL("http://argo")
        .lastDeployed(LocalDate.now())
        .build(),
      AppStateDTO.builder()
        .appName("app2")
        .appVersion("2.0.0")
        .appHealth("UP")
        .grafanaURL("http://grafana")
        .argoURL("http://argo")
        .lastDeployed(LocalDate.now())
        .build(),
      AppStateDTO.builder()
        .appName("app3")
        .appVersion("3.0.0")
        .appHealth("UP")
        .grafanaURL("http://grafana")
        .argoURL("http://argo")
        .lastDeployed(LocalDate.now())
        .build()
    );
  }

}
