package cds.gen.travelservice;

import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.services.EventContext;
import com.sap.cds.services.EventName;
import java.lang.String;
import javax.annotation.processing.Generated;

@EventName("rejectTravel")
@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
public interface TravelRejectTravelContext extends EventContext {
  String CDS_NAME = "rejectTravel";

  CqnSelect cqn();

  TravelRejectTravelContext cqn(CqnSelect select);

  static TravelRejectTravelContext create() {
    return EventContext.create(TravelRejectTravelContext.class, "TravelService.Travel");
  }

  static TravelRejectTravelContext create(String entityName) {
    return EventContext.create(TravelRejectTravelContext.class, entityName);
  }
}
