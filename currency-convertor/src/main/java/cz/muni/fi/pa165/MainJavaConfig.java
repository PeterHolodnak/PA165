package cz.muni.fi.pa165;

import cz.muni.fi.pa165.config.SpringConfiguration;
import cz.muni.fi.pa165.currency.CurrencyConvertorImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.util.Currency;

public class MainJavaConfig {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfiguration.class);

        CurrencyConvertorImpl currencyConvertor = ctx.getBean(CurrencyConvertorImpl.class);

        BigDecimal convertedValue = currencyConvertor.convert(Currency.getInstance("EUR"), Currency.getInstance("CZK"), new BigDecimal("1"));
        System.out.println(convertedValue);
    }
}

