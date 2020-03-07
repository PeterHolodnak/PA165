package cz.muni.fi.pa165.config;

import cz.muni.fi.pa165.currency.CurrencyConvertorImpl;
import cz.muni.fi.pa165.currency.ExchangeRateTable;
import cz.muni.fi.pa165.currency.ExchangeRateTableImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.inject.Inject;

@Configuration
@ComponentScan("cz.muni.fi.pa165")
@EnableAspectJAutoProxy
public class SpringConfiguration {
    @Inject
    private CurrencyConvertorImpl currencyConvertor;
    /* Commented beans, because they are created in xml configuration as well and it throws an error */
//    @Bean
//    public CurrencyConvertorImpl currencyConvertor(ExchangeRateTable exchangeRateTable) {
//        return new CurrencyConvertorImpl(exchangeRateTable);
//    }

    @Bean
    public ExchangeRateTableImpl exchangeRateTable() {
        return new ExchangeRateTableImpl();
    }
}