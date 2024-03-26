package global.hdi.appoverview.apps;

import global.hdi.appoverview.apps.config.Stage;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AppStateDTO {
  private static final String READINESS = "/actuator/health/readiness";
  private static final String LIVENESS = "/actuator/health/liveness";
  //dev
  private Stage stage;
  //acc004
  private String space;
  //ecert
  private String appName;

  private String appVersion;
  //https://ecert.global-apps-1.gwc.azure.ew.zz/
  private String appHealth;
  private String grafanaURL;
  private String argoURL;
  private LocalDate lastDeployed;
  //https://ecert.global-apps-1.gwc.azure.ew.zz/contract
  //
  //https://grafana-acc004.global-apps-1.gwc.azure.ew.zz/explore
  //https://grafana-%s.global-apps-1.gwc.azure.ew.zz/explore
  //https://argocd.global-apps-1.gwc.azure.ew.zz/applications/argocd/acc004-ecert-ecert-backend
  public String getHealthEndpoint(){
    return getAppURL() + LIVENESS;
  }
  public String getReadinessEndpoint(){
    return getAppURL() + READINESS;
  }

  public String getAppURL() {
    return getStage().getAppURL().formatted(getAppName());
  }
}
