package com.sainsburys;

import org.joda.money.Money;

public class Product {
	private String title;
    private Money unitPrice;
    private String size;
    private String description;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Money getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Money unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
}
