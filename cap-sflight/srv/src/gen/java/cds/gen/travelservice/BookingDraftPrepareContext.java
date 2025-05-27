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
public interface BookingDraftPrepareContext extends EventContext {
  String SIDE_EFFECTS_QUALIFIER = "SideEffectsQualifier";

  String CDS_NAME = "draftPrepare";

  @CdsName(SIDE_EFFECTS_QUALIFIER)
  String sideEffectsQualifier();

  @CdsName(SIDE_EFFECTS_QUALIFIER)
  BookingDraftPrepareContext sideEffectsQualifier(String sideEffectsQualifier);

  CqnSelect cqn();

  BookingDraftPrepareContext cqn(CqnSelect select);

  static BookingDraftPrepareContext create() {
    return EventContext.create(BookingDraftPrepareContext.class, "TravelService.Booking");
  }

  BookingDraftPrepareContext result(Booking result);

  Booking result();

  static BookingDraftPrepareContext create(String entityName) {
    return EventContext.create(BookingDraftPrepareContext.class, entityName);
  }
}
