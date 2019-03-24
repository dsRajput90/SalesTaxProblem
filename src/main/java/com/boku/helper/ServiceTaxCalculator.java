package com.boku.helper;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.boku.dto.Item;

/**
 * Helper class which contains static methods for calculating tax as per the item details passed and returns sales tax
 * */
@Component
public class ServiceTaxCalculator {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceTaxCalculator.class);
	
	private static final BigDecimal ROUNDOFF = new BigDecimal("0.05");
	private static final BigDecimal PERCENTAGE = new BigDecimal("100.00");

	public static BigDecimal calculateTax(Item item) {
        BigDecimal salesTax = item.getItemPrice().multiply(item.getTaxPercentage()).divide(PERCENTAGE);
        salesTax = roundOff(salesTax);
        LOGGER.debug("ServiceTaxCalculator :: sales tax for item {} is {}", item.getItemName(), salesTax);
        return roundOff(salesTax);
    }
	
	private static BigDecimal roundOff(BigDecimal value) {
        value = value.divide(ROUNDOFF);
        value = new BigDecimal(Math.ceil(value.doubleValue()));
        value = value.multiply(ROUNDOFF);
        return value;
    }

}
