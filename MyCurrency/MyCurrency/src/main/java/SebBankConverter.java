import lt.itakademija.exam.*;

import java.util.HashMap;

public class SebBankConverter implements CurrencyConverter {
  private final CurrencyRatesProvider currencyRatesProvider;

  public SebBankConverter(CurrencyRatesProvider currencyRatesProvider) {
    this.currencyRatesProvider = currencyRatesProvider;
  }

  @Override
  public Money convert(Currency currency, Currency currency1, Money money) {
    Money rate = currencyRatesProvider.getRate(currency, currency1);

    if (rate == null) {
      throw new CurrencyConversionException("Cannot convert the given currency pair");
    }

    return money.multiply(rate);
  }
}
