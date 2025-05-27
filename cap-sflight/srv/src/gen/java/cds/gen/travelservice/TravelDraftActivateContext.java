package cds.gen.travelservice;

import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.services.EventContext;
import com.sap.cds.services.EventName;
import java.lang.String;
import javax.annotation.processing.Generated;

@EventName("draftActivate")
@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
public interface TravelDraftActivateContext extends EventContext {
  String CDS_NAME = "draftActivate";

  CqnSelect cqn();

  TravelDraftActivateContext cqn(CqnSelect select);

  static TravelDraftActivateContext create() {
    return EventContext.create(TravelDraftActivateContext.class, "TravelService.Travel");
  }

  TravelDraftActivateContext result(Travel result);

  Travel result();

  static TravelDraftActivateContext create(String entityName) {
    return EventContext.create(TravelDraftActivateContext.class, entityName);
  }
}
