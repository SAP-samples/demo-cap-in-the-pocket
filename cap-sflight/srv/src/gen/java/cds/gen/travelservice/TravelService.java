package cds.gen.travelservice;

import com.sap.cds.ql.CdsName;
import com.sap.cds.services.cds.ApplicationService;
import com.sap.cds.services.cds.CqnService;
import com.sap.cds.services.cds.RemoteService;
import com.sap.cds.services.draft.DraftService;
import java.lang.Integer;
import javax.annotation.processing.Generated;

@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
@CdsName(TravelService_.CDS_NAME)
public interface TravelService extends CqnService {
  @CdsName(TravelRejectTravelContext.CDS_NAME)
  void rejectTravel(Travel_ ref);

  @CdsName(TravelAcceptTravelContext.CDS_NAME)
  void acceptTravel(Travel_ ref);

  @CdsName(TravelCreateTravelByTemplateContext.CDS_NAME)
  Travel createTravelByTemplate(Travel_ ref);

  @CdsName(TravelDeductDiscountContext.CDS_NAME)
  Travel deductDiscount(Travel_ ref, @CdsName(TravelDeductDiscountContext.PERCENT) Integer percent);

  interface Application extends ApplicationService, TravelService {
  }

  interface Remote extends RemoteService, TravelService {
  }

  interface Draft extends DraftService, TravelService {
  }
}
