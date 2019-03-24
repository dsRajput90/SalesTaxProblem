package com.boku.helper;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.boku.dto.Item;

/**
 * Helper class which prints the result of sales tax calculation in specific format to the console.
 * */
@Component
public class PrintSalesTaxHelper {

	private static final Logger LOGGER = LoggerFactory.getLogger(PrintSalesTaxHelper.class);
	
	public static void printSingleTaxAmount(Item item, BigDecimal taxAmount) {
		String taxFormat = String.format("%s: %.2f",item.getItemName(),taxAmount);
		LOGGER.info(taxFormat);
		System.out.println(taxFormat);
	}

	public static void printTotalTax(BigDecimal totalTaxAmount) {
		String totalTaxFormat = String.format("Sales Taxes: %.2f", totalTaxAmount);
		LOGGER.info(totalTaxFormat);
		System.out.println(totalTaxFormat);
	}

	public static void printTotalAmount(BigDecimal totalAmt) {
		String totalFormat = String.format("Total: %.2f\n", totalAmt);
		LOGGER.info(totalFormat);
		System.out.println(totalFormat);
	}

}
