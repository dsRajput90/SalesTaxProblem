package com.boku.dto;

import java.math.BigDecimal;

public class Item {

	private String itemName;
	private BigDecimal itemPrice;
	private boolean isExempted;
	private boolean isImported;
	private BigDecimal taxPercentage;
	
	public BigDecimal getTaxPercentage() {
		return taxPercentage;
	}
	public void setTaxPercentage(BigDecimal taxPercentage) {
		this.taxPercentage = taxPercentage;
	}
	public boolean isExempted() {
		return isExempted;
	}
	public void setExempted(boolean isExempted) {
		this.isExempted = isExempted;
	}
	public boolean isImported() {
		return isImported;
	}
	public void setImported(boolean isImported) {
		this.isImported = isImported;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public BigDecimal getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(BigDecimal itemPrice) {
		this.itemPrice = itemPrice;
	}
	
}
