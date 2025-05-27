package cds.gen.analyticsservice;

import com.sap.cds.ql.CdsName;
import com.sap.cds.services.cds.ApplicationService;
import com.sap.cds.services.cds.CqnService;
import com.sap.cds.services.cds.RemoteService;
import com.sap.cds.services.draft.DraftService;
import javax.annotation.processing.Generated;

@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
@CdsName(AnalyticsService_.CDS_NAME)
public interface AnalyticsService extends CqnService {
  interface Application extends ApplicationService, AnalyticsService {
  }

  interface Remote extends RemoteService, AnalyticsService {
  }

  interface Draft extends DraftService, AnalyticsService {
  }
}
