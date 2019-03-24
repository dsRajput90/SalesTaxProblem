package com.boku.service;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static org.springframework.util.StringUtils.isEmpty;
import static com.boku.helper.PrintSalesTaxHelper.*;

import com.boku.dto.Item;
import com.boku.exception.SalesTaxException;
import com.boku.helper.ServiceTaxCalculator;
import com.boku.util.FileReaderUtility;
import com.boku.util.ResponseCodes;

/**
 * Service class which handles the logic for sales tax calculation
 * */
@Service
public class SalesTaxService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SalesTaxService.class);

	@Autowired
	private ItemDetailsService itemDetailsService;
	
	@Autowired
	private FileReaderUtility fileReader;
	
	@Value("${file.count}")
    private Integer fileCount;
	
	@Value("${file.path}")
    private String filePath;
	
	@Value("${file.type}")
	private String fileType;
	
	public String calculateSalesTax() {
		LOGGER.debug("SalesTaxService :: Running the program to calculate SalesTax for {}",filePath);
		String response = null;
		if(isValidFileProperties()) {
			response = processEachFile();
		}else {
			response = ResponseCodes.EMPTY.getValue();
		}
		return response;
	}
	
	private boolean isValidFileProperties() {
		return fileCount>0 && !isEmpty(filePath) && !isEmpty(fileType);
	}
	
	private String processEachFile() {
		List<String> items = null;
		String response = null;
		for(int i=0;i<fileCount;) {
			try {
				items = getItemsToCalculate(filePath + ++i + fileType);
			}catch(SalesTaxException e) {
				return e.getMessage();
			}
			if(items.isEmpty()) {
				LOGGER.info("SalesTaxService :: File list is empty {}",filePath);
				LOGGER.info("SalesTaxService :: Nothing to calculate");
				response = ResponseCodes.EMPTY_FILE.getValue();
			}else {
				response = calculateAndPrintTax(items);
			}
		}

		return response;
	}
	
	private List<String> getItemsToCalculate(String fileName) throws SalesTaxException{
		List<String> items = null;
		try {
			items = fileReader.readFile(fileName);	
		}catch(Exception e) {
			LOGGER.error("SalesTaxService :: getItemsToCalculate :: Exception reading from the file", e);
			throw new SalesTaxException(ResponseCodes.FILE_INPUT_ERROR.getValue(), e);
		}
		return items;
	}
	
	private String calculateAndPrintTax(List<String> items) {
		String response = ResponseCodes.SUCCESS.getValue();
		List<Item> itemDetails = itemDetailsService.getItemDetails(items); 
		if(itemDetails.isEmpty()) {
			LOGGER.debug("SalesTaxService :: calculateAndPrintTax :: item details is empty. Nothing to calculate");
			response = ResponseCodes.EMPTY.getValue();
		}else {
			LOGGER.debug("SalesTaxService :: calculateAndPrintTax :: Calculate tax for items");
			BigDecimal sum = BigDecimal.valueOf(0.00);
			BigDecimal totalTax = new BigDecimal("0.00");
			for(Item item: itemDetails) {
				BigDecimal taxAmount = ServiceTaxCalculator.calculateTax(item);
				BigDecimal totalAmount = item.getItemPrice().add(taxAmount);
				totalTax = totalTax.add(taxAmount);
				sum = sum.add(totalAmount);
				printSingleTaxAmount(item, totalAmount);
			}
			
			printTotalTax(totalTax);
			printTotalAmount(sum);
		}
		return response;
	}
}
