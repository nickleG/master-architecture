package global.hdi.appoverview.apps.config;

import lombok.Getter;

@Getter
public enum Stage {
  DEV("dev",
      "https://argocd.global-apps-1.gwc.azure.ew.zz/applications/argocd",
      "https://grafana-acc004.global-apps-1.gwc.azure.ew.zz/explore",
      "https://%s.global-apps-1.gwc.azure.ew.zz/"),
  UAT("uat","","", ""),
  PROD("prod","","", "");


  /**
   *
   */
  private final String name;
  private final String argoURL;
  private final String grafanaURL;
  private final String appURL;
  Stage(String name, String argoURL, String grafanaURL, String appURL) {
    this.name = name;
    this.argoURL = argoURL;
    this.grafanaURL = grafanaURL;
    this.appURL = appURL;
  }


}
