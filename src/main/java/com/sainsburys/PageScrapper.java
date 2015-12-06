package com.sainsburys;

import java.io.IOException;
import java.util.LinkedList;

import org.joda.money.Money;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PageScrapper {
	
	private Money total;
	private PageScrapperService pageScrapperService;
	
	public PageScrapper(){}

	public LinkedList<Product> fetchWebData() throws IOException{
		LinkedList<Product> products  = new LinkedList<Product>();

		pageScrapperService = new PageScrapperServiceImpl();
		Document document = pageScrapperService.fetchDocument(WebConstants.URL);
		Element element = pageScrapperService.fetchFirstElement(document, WebConstants.PRODUCT_LIST);
		Elements hrefElements = pageScrapperService.fetchElementList(element,WebConstants.PRODUCT_INFO);

		total = Money.of(WebConstants.GBP, 0);

		for (Element hrefElement : hrefElements) {
			Product product = fetchProductDetails(hrefElement);
			products.add(product);
			total = total.plus(product.getUnitPrice());
		}
		
		return products;
	}
	
	private Product fetchProductDetails(Element hrefElement) throws IOException{
		Product product = new Product();
		String detailsUrl = pageScrapperService.extractHref(hrefElement,WebConstants.PRODUCT_DETAILS, WebConstants.PRODUCT_DETAILS_ATTRIBUTE);
		Document details = pageScrapperService.fetchDocument(detailsUrl);
		product.setTitle(pageScrapperService.fetchDetails(details, WebConstants.PRODUCT_TITLE));
		product.setDescription(pageScrapperService.fetchDetails(details, WebConstants.PRODUCT_DESCRIPTION));
		product.setSize(pageScrapperService.fetchDocumentSize(details, WebConstants.TEMP_HTML_FILE_NAME));
		product.setUnitPrice(pageScrapperService.fetchUnitPrice(details, WebConstants.PRODUCT_UNIT_PRICE, WebConstants.PRODUCT_UNIT_PRICE_ATTRIBUTE, WebConstants.GBP));

		return product;
	}

	public Money getTotal() {
		return total;
	}
}
