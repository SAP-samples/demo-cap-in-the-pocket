package cds.gen.sap.fe.cap.travel;

import cds.gen.sap.common.Currencies_;
import com.sap.cds.ql.CdsName;
import com.sap.cds.ql.ElementRef;
import com.sap.cds.ql.StructuredType;
import com.sap.cds.ql.cqn.CqnPredicate;
import java.lang.String;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.function.Function;
import javax.annotation.processing.Generated;

@CdsName("sap.fe.cap.travel.Supplement")
@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
public interface Supplement_ extends StructuredType<Supplement_> {
  String LAST_CHANGED_AT = "LastChangedAt";

  String LAST_CHANGED_BY = "LastChangedBy";

  String SUPPLEMENT_ID = "SupplementID";

  String PRICE = "Price";

  String TYPE_CODE = "Type_code";

  String DESCRIPTION = "Description";

  String CURRENCY_CODE_CODE = "CurrencyCode_code";

  String CDS_NAME = "sap.fe.cap.travel.Supplement";

  ElementRef<Instant> createdAt();

  ElementRef<String> createdBy();

  @CdsName(LAST_CHANGED_AT)
  ElementRef<Instant> LastChangedAt();

  @CdsName(LAST_CHANGED_BY)
  ElementRef<String> LastChangedBy();

  @CdsName(SUPPLEMENT_ID)
  ElementRef<String> SupplementID();

  @CdsName(PRICE)
  ElementRef<BigDecimal> Price();

  SupplementType_ Type();

  SupplementType_ Type(Function<SupplementType_, CqnPredicate> filter);

  @CdsName(TYPE_CODE)
  ElementRef<String> Type_code();

  @CdsName(DESCRIPTION)
  ElementRef<String> Description();

  Currencies_ CurrencyCode();

  Currencies_ CurrencyCode(Function<Currencies_, CqnPredicate> filter);

  @CdsName(CURRENCY_CODE_CODE)
  ElementRef<String> CurrencyCode_code();

  SupplementTexts_ texts();

  SupplementTexts_ texts(Function<SupplementTexts_, CqnPredicate> filter);

  SupplementTexts_ localized();

  SupplementTexts_ localized(Function<SupplementTexts_, CqnPredicate> filter);
}
