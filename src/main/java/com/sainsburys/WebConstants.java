package com.sainsburys;

import org.joda.money.CurrencyUnit;

public class WebConstants {
	public static final String URL = "http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html";
	public static final String PRODUCT_LIST = ".listView";
	public static final String PRODUCT_INFO = ".productInfo";
	public static final String PRODUCT_DETAILS = "h3 a";
	public static final String PRODUCT_DETAILS_ATTRIBUTE = "href"; 
	public static final String PRODUCT_TITLE = ".productTitleDescriptionContainer h1";
	public static final String PRODUCT_DESCRIPTION = "#information .productText";
	public static final String PRODUCT_UNIT_PRICE = ".pricePerUnit";
	public static final String PRODUCT_UNIT_PRICE_ATTRIBUTE = "abbr";
	public static final String TEMP_HTML_FILE_NAME = "tempDetailsPageFile";
	public static final CurrencyUnit GBP = CurrencyUnit.of("GBP");
}
