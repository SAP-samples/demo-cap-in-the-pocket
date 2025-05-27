package cds.gen.travelservice;

import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.services.EventContext;
import com.sap.cds.services.EventName;
import java.lang.String;
import javax.annotation.processing.Generated;

@EventName("createTravelByTemplate")
@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
public interface TravelCreateTravelByTemplateContext extends EventContext {
  String CDS_NAME = "createTravelByTemplate";

  CqnSelect cqn();

  TravelCreateTravelByTemplateContext cqn(CqnSelect select);

  static TravelCreateTravelByTemplateContext create() {
    return EventContext.create(TravelCreateTravelByTemplateContext.class, "TravelService.Travel");
  }

  TravelCreateTravelByTemplateContext result(Travel result);

  Travel result();

  static TravelCreateTravelByTemplateContext create(String entityName) {
    return EventContext.create(TravelCreateTravelByTemplateContext.class, entityName);
  }
}
