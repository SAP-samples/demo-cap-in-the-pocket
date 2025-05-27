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
public interface BookingSupplementDraftPrepareContext extends EventContext {
  String SIDE_EFFECTS_QUALIFIER = "SideEffectsQualifier";

  String CDS_NAME = "draftPrepare";

  @CdsName(SIDE_EFFECTS_QUALIFIER)
  String sideEffectsQualifier();

  @CdsName(SIDE_EFFECTS_QUALIFIER)
  BookingSupplementDraftPrepareContext sideEffectsQualifier(String sideEffectsQualifier);

  CqnSelect cqn();

  BookingSupplementDraftPrepareContext cqn(CqnSelect select);

  static BookingSupplementDraftPrepareContext create() {
    return EventContext.create(BookingSupplementDraftPrepareContext.class, "TravelService.BookingSupplement");
  }

  BookingSupplementDraftPrepareContext result(BookingSupplement result);

  BookingSupplement result();

  static BookingSupplementDraftPrepareContext create(String entityName) {
    return EventContext.create(BookingSupplementDraftPrepareContext.class, entityName);
  }
}
