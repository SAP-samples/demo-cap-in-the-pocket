package cds.gen.travelservice;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.services.EventContext;
import com.sap.cds.services.EventName;
import java.lang.String;
import javax.annotation.processing.Generated;

@EventName("draftPrepare")
@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
public interface TravelDraftPrepareContext extends EventContext {
  String SIDE_EFFECTS_QUALIFIER = "SideEffectsQualifier";

  String CDS_NAME = "draftPrepare";

  @CdsName(SIDE_EFFECTS_QUALIFIER)
  String sideEffectsQualifier();

  @CdsName(SIDE_EFFECTS_QUALIFIER)
  TravelDraftPrepareContext sideEffectsQualifier(String sideEffectsQualifier);

  CqnSelect cqn();

  TravelDraftPrepareContext cqn(CqnSelect select);

  static TravelDraftPrepareContext create() {
    return EventContext.create(TravelDraftPrepareContext.class, "TravelService.Travel");
  }

  TravelDraftPrepareContext result(Travel result);

  Travel result();

  static TravelDraftPrepareContext create(String entityName) {
    return EventContext.create(TravelDraftPrepareContext.class, entityName);
  }
}
