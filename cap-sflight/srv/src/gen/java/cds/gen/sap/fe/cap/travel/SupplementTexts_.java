package cds.gen.sap.fe.cap.travel;

import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.StructuredType;
import java.lang.String;
import javax.annotation.processing.Generated;

@CdsName("sap.fe.cap.travel.Supplement.texts")
@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
public interface SupplementTexts_ extends StructuredType<SupplementTexts_> {
  String SUPPLEMENT_ID = "SupplementID";

  String DESCRIPTION = "Description";

  String CDS_NAME = "sap.fe.cap.travel.Supplement.texts";

  ElementRef<String> locale();

  @CdsName(SUPPLEMENT_ID)
  ElementRef<String> SupplementID();

  @CdsName(DESCRIPTION)
  ElementRef<String> Description();
}
