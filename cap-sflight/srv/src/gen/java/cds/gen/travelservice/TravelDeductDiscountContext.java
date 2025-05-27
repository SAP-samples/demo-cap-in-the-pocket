package cds.gen.travelservice;

import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.services.EventContext;
import com.sap.cds.services.EventName;
import java.lang.Integer;
import java.lang.String;
import javax.annotation.processing.Generated;

@EventName("deductDiscount")
@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
public interface TravelDeductDiscountContext extends EventContext {
  String PERCENT = "percent";

  String CDS_NAME = "deductDiscount";

  Integer percent();

  TravelDeductDiscountContext percent(Integer percent);

  CqnSelect cqn();

  TravelDeductDiscountContext cqn(CqnSelect select);

  static TravelDeductDiscountContext create() {
    return EventContext.create(TravelDeductDiscountContext.class, "TravelService.Travel");
  }

  TravelDeductDiscountContext result(Travel result);

  Travel result();

  static TravelDeductDiscountContext create(String entityName) {
    return EventContext.create(TravelDeductDiscountContext.class, entityName);
  }
}
