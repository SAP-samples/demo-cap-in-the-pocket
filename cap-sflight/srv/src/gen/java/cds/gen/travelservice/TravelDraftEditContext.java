package cds.gen.travelservice;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.services.EventContext;
import com.sap.cds.services.EventName;
import java.lang.Boolean;
import java.lang.String;
import javax.annotation.processing.Generated;

@EventName("draftEdit")
@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
public interface TravelDraftEditContext extends EventContext {
  String PRESERVE_CHANGES = "PreserveChanges";

  String CDS_NAME = "draftEdit";

  @CdsName(PRESERVE_CHANGES)
  Boolean preserveChanges();

  @CdsName(PRESERVE_CHANGES)
  TravelDraftEditContext preserveChanges(Boolean preserveChanges);

  CqnSelect cqn();

  TravelDraftEditContext cqn(CqnSelect select);

  static TravelDraftEditContext create() {
    return EventContext.create(TravelDraftEditContext.class, "TravelService.Travel");
  }

  TravelDraftEditContext result(Travel result);

  Travel result();

  static TravelDraftEditContext create(String entityName) {
    return EventContext.create(TravelDraftEditContext.class, entityName);
  }
}
