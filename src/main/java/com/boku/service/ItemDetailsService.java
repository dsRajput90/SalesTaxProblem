package com.boku.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.boku.dto.Item;
import com.boku.helper.SalesTaxType;
/**
 * Service class which handles the logic to get the item details and map it to Item DTO for further execution.
 * */
@Service
public class ItemDetailsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ItemDetailsService.class);
	
	public List<Item> getItemDetails(List<String> items){
		LOGGER.debug("ItemDetailsService :: getItemDetails :: split and get item name and price ");
		List<Item> itemDetails = new ArrayList<Item>(items.size());
		
		for(String itemDetail: items) {
			String[] details = itemDetail.split("(\\sat\\s)");
			if(details!=null && details.length>=2) {
	            Item item = new Item();
	            item.setItemName(details[0]);
	            item.setItemPrice(new BigDecimal(details[1]));
	            item.setExempted(isItemExempted(item));
	            item.setImported(isItemImported(item));
	            item.setTaxPercentage(getTaxPercentage(item));
	            itemDetails.add(item);
			}
		}
		LOGGER.debug("ItemDetailsService :: getItemDetails :: Setting value in the map with item name and price done :: itemDetails :: {}", itemDetails);
		
		return itemDetails;
	}
	
	private boolean isItemExempted(Item item) {
		Pattern pattern = Pattern.compile("(.*(book|chocolate|pills|food).*)");
		boolean isExempted = pattern.matcher(item.getItemName()).matches();
		LOGGER.debug("ItemDetailsService :: isItemExempted :: Item name {} isExempted value is {}", item.getItemName(), isExempted);
		return isExempted;
        
	}
	
	private boolean isItemImported(Item item) {
		Pattern pattern = Pattern.compile("(.*imported.*)");
		boolean isImported = pattern.matcher(item.getItemName()).matches();
		LOGGER.debug("ItemDetailsService :: isItemImported :: Item name {} isItemImported value is {}", item.getItemName(), isImported);
		return isImported;
        
	}

	private BigDecimal getTaxPercentage(Item item) {
		BigDecimal taxPercentage = SalesTaxType.EXEMPTED.apply(item).add(SalesTaxType.IMPORTED.apply(item));
		LOGGER.debug("ItemDetailsService :: getTaxPercentage :: Item name {} taxPercentage value is {}", item.getItemName(), taxPercentage);
		return taxPercentage;
	}
}
