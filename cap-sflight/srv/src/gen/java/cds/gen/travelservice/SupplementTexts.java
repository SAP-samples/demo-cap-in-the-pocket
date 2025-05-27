package cds.gen.travelservice;

import com.sap.cds.CdsData;
import com.sap.cds.Struct;
import com.sap.cds.ql.CdsName;
import java.lang.Object;
import java.lang.String;
import java.util.Map;
import javax.annotation.processing.Generated;

@CdsName("TravelService.Supplement.texts")
@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
public interface SupplementTexts extends CdsData {
  String LOCALE = "locale";

  String SUPPLEMENT_ID = "SupplementID";

  String DESCRIPTION = "Description";

  /**
   * Type for a language code
   */
  String locale();

  /**
   * Type for a language code
   */
  SupplementTexts locale(String locale);

  @CdsName(SUPPLEMENT_ID)
  String supplementID();

  @CdsName(SUPPLEMENT_ID)
  SupplementTexts supplementID(String supplementID);

  @CdsName(DESCRIPTION)
  String description();

  @CdsName(DESCRIPTION)
  SupplementTexts description(String description);

  SupplementTexts_ ref();

  static SupplementTexts create() {
    return Struct.create(SupplementTexts.class);
  }

  static SupplementTexts of(Map<String, Object> map) {
    return Struct.access(map).as(SupplementTexts.class);
  }
}
