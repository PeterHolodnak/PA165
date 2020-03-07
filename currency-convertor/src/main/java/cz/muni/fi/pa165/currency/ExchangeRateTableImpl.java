package cz.muni.fi.pa165.currency;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

public class ExchangeRateTableImpl implements ExchangeRateTable  {

    @Override
    public BigDecimal getExchangeRate(Currency sourceCurrency, Currency targetCurrency) throws ExternalServiceFailureException {
        Objects.requireNonNull(sourceCurrency);
        Objects.requireNonNull(targetCurrency);

        if (sourceCurrency.getCurrencyCode().equals("EUR") && targetCurrency.getCurrencyCode().equals("CZK")) {
            return BigDecimal.valueOf(27);
        }

        return null;
    }
}
