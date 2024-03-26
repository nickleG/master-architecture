package global.hdi.appoverview.apps;

import static org.junit.jupiter.api.Assertions.*;

import global.hdi.appoverview.apps.config.Stage;
import org.junit.jupiter.api.Test;

class AppStateDTOTest {
/*
 //dev
  private Stage stage;
  //acc004
  private String space;
  //ecert
  private String appName;

  private String appVersion;
  //https://ecert.global-apps-1.gwc.azure.ew.zz/
  private String appURL;
  private String appHealth;
  private String grafanaURL;
  private String argoURL;
  private LocalDate lastDeployed;
  //https://ecert.global-apps-1.gwc.azure.ew.zz/contract
  //
  //https://grafana-acc004.global-apps-1.gwc.azure.ew.zz/explore
  //https://grafana-%s.global-apps-1.gwc.azure.ew.zz/explore
  //https://argocd.global-apps-1.gwc.azure.ew.zz/applications/argocd/acc004-ecert-ecert-backend
 */
  @Test
  void shouldReturnURLs(){

    AppStateDTO dto = AppStateDTO.builder().stage(Stage.DEV)
        .space("acc004")
        .appName("ecert")
        .build();

    var ep = dto.getHealthEndpoint();
    var df = dto.getReadinessEndpoint();

    System.out.println(ep);
    System.out.println(df);
    System.out.println(dto.getAppURL());


  }

}