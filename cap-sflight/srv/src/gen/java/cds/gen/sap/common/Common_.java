package cds.gen.sap.common;

import com.sap.cds.ql.CdsName;
import java.lang.Class;
import java.lang.String;
import javax.annotation.processing.Generated;

@Generated(
    value = "cds-maven-plugin",
    date = "2025-05-08T06:34:56.366350Z",
    comments = "com.sap.cds:cds-maven-plugin:3.9.1 / com.sap.cds:cds4j-api:3.9.1"
)
@CdsName("sap.common")
public interface Common_ {
  String CDS_NAME = "sap.common";

  Class<Languages_> LANGUAGES = Languages_.class;

  Class<CountriesTexts_> COUNTRIES_TEXTS = CountriesTexts_.class;

  Class<LanguagesTexts_> LANGUAGES_TEXTS = LanguagesTexts_.class;

  Class<TimezonesTexts_> TIMEZONES_TEXTS = TimezonesTexts_.class;

  Class<Countries_> COUNTRIES = Countries_.class;

  Class<Timezones_> TIMEZONES = Timezones_.class;

  Class<Currencies_> CURRENCIES = Currencies_.class;

  Class<CurrenciesTexts_> CURRENCIES_TEXTS = CurrenciesTexts_.class;
}
