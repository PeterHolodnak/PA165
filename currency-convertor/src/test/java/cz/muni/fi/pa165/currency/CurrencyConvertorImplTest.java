package cz.muni.fi.pa165.currency;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Currency;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Fail.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyConvertorImplTest {

    @Mock
    private ExchangeRateTable exchangeRateTable;
    private static CurrencyConvertor currencyConvertor;

    private static final Currency SOURCE_CURRENCY = Currency.getInstance("MNT");
    private static final Currency TARGET_CURRENCY = Currency.getInstance("KHR");


    @Before
    public void createCurrencyConvertor() {
        currencyConvertor = new CurrencyConvertorImpl(exchangeRateTable);
    }

    @Test
    public void testConvert() throws ExternalServiceFailureException {
        // Don't forget to test border values and proper rounding.

        when(exchangeRateTable.getExchangeRate(SOURCE_CURRENCY, TARGET_CURRENCY))
            .thenReturn(new BigDecimal("0.01"));

//        BigDecimal actualResult = currencyConvertor.convert(SOURCE_CURRENCY, TARGET_CURRENCY, BigDecimal.valueOf(150, 2));
//        BigDecimal expectedResult = new BigDecimal("0.02");
//        assertThat(actualResult).isEqualTo(expectedResult);

        assertThat(currencyConvertor.convert(SOURCE_CURRENCY, TARGET_CURRENCY, BigDecimal.valueOf(150, 2))).isEqualTo("0.02");
    }

    @Test
    public void testConvertWithNullSourceCurrency() {
        fail("Test is not implemented yet.");
    }

    @Test
    public void testConvertWithNullTargetCurrency() {
        fail("Test is not implemented yet.");
    }

    @Test
    public void testConvertWithNullSourceAmount() {
        fail("Test is not implemented yet.");
    }

    @Test
    public void testConvertWithUnknownCurrency() {
        fail("Test is not implemented yet.");
    }

    @Test
    public void testConvertWithExternalServiceFailure() throws ExternalServiceFailureException {
        ExternalServiceFailureException externalServiceFailureException = new ExternalServiceFailureException("Some suitable message");
        when(exchangeRateTable.getExchangeRate(SOURCE_CURRENCY, TARGET_CURRENCY))
            .thenThrow(externalServiceFailureException);

        assertThatExceptionOfType(UnknownExchangeRateException.class)
            .isThrownBy(() -> currencyConvertor.convert(SOURCE_CURRENCY, TARGET_CURRENCY, BigDecimal.valueOf(150, 2)))
            .withCause(externalServiceFailureException)
            .withMessage("Some suitable message");
    }

}
