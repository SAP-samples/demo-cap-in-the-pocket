package cds.gen.travelservice;

import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.services.EventContext;
import com.sap.cds.services.EventName;
import java.lang.String;
import javax.annotation.processing.Generated;

@EventName("acceptTravel")
@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
public interface TravelAcceptTravelContext extends EventContext {
  String CDS_NAME = "acceptTravel";

  CqnSelect cqn();

  TravelAcceptTravelContext cqn(CqnSelect select);

  static TravelAcceptTravelContext create() {
    return EventContext.create(TravelAcceptTravelContext.class, "TravelService.Travel");
  }

  static TravelAcceptTravelContext create(String entityName) {
    return EventContext.create(TravelAcceptTravelContext.class, entityName);
  }
}
