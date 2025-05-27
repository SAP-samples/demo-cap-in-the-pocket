package cds.gen.travelservice;

import com.sap.cds.CdsData;
import com.sap.cds.Struct;
import com.sap.cds.ql.CdsName;
import java.lang.Object;
import java.lang.String;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;

@CdsName("TravelService.Supplement")
@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
public interface Supplement extends CdsData {
  String CREATED_AT = "createdAt";

  String CREATED_BY = "createdBy";

  String LAST_CHANGED_AT = "LastChangedAt";

  String LAST_CHANGED_BY = "LastChangedBy";

  String SUPPLEMENT_ID = "SupplementID";

  String PRICE = "Price";

  String TYPE = "Type";

  String TYPE_CODE = "Type_code";

  String DESCRIPTION = "Description";

  String CURRENCY_CODE = "CurrencyCode";

  String CURRENCY_CODE_CODE = "CurrencyCode_code";

  String TEXTS = "texts";

  String LOCALIZED = "localized";

  Instant createdAt();

  Supplement createdAt(Instant createdAt);

  /**
   * Canonical user ID
   */
  String createdBy();

  /**
   * Canonical user ID
   */
  Supplement createdBy(String createdBy);

  @CdsName(LAST_CHANGED_AT)
  Instant lastChangedAt();

  @CdsName(LAST_CHANGED_AT)
  Supplement lastChangedAt(Instant lastChangedAt);

  /**
   * Canonical user ID
   */
  @CdsName(LAST_CHANGED_BY)
  String lastChangedBy();

  /**
   * Canonical user ID
   */
  @CdsName(LAST_CHANGED_BY)
  Supplement lastChangedBy(String lastChangedBy);

  @CdsName(SUPPLEMENT_ID)
  String supplementID();

  @CdsName(SUPPLEMENT_ID)
  Supplement supplementID(String supplementID);

  @CdsName(PRICE)
  BigDecimal price();

  @CdsName(PRICE)
  Supplement price(BigDecimal price);

  @CdsName(TYPE)
  SupplementType type();

  @CdsName(TYPE)
  Supplement type(Map<String, ?> type);

  @CdsName(TYPE_CODE)
  String typeCode();

  @CdsName(TYPE_CODE)
  Supplement typeCode(String typeCode);

  @CdsName(DESCRIPTION)
  String description();

  @CdsName(DESCRIPTION)
  Supplement description(String description);

  /**
   * Type for an association to Currencies
   *
   * See https://cap.cloud.sap/docs/cds/common#type-currency
   */
  @CdsName(CURRENCY_CODE)
  Currencies currencyCode();

  /**
   * Type for an association to Currencies
   *
   * See https://cap.cloud.sap/docs/cds/common#type-currency
   */
  @CdsName(CURRENCY_CODE)
  Supplement currencyCode(Map<String, ?> currencyCode);

  @CdsName(CURRENCY_CODE_CODE)
  String currencyCodeCode();

  @CdsName(CURRENCY_CODE_CODE)
  Supplement currencyCodeCode(String currencyCodeCode);

  List<SupplementTexts> texts();

  Supplement texts(List<? extends Map<String, ?>> texts);

  SupplementTexts localized();

  Supplement localized(Map<String, ?> localized);

  Supplement_ ref();

  static Supplement create() {
    return Struct.create(Supplement.class);
  }

  static Supplement of(Map<String, Object> map) {
    return Struct.access(map).as(Supplement.class);
  }

  static Supplement create(String supplementID) {
    Map<String, Object> keys = new HashMap<>();
    keys.put(SUPPLEMENT_ID, supplementID);
    return Struct.access(keys).as(Supplement.class);
  }
}
