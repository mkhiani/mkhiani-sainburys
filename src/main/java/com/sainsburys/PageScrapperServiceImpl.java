package com.sainsburys;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PageScrapperServiceImpl implements PageScrapperService{
	
	public PageScrapperServiceImpl(){}

	public Document fetchDocument(final String httpAddress) throws IOException{
		return Jsoup.connect(httpAddress).get();
	}

	public Element fetchFirstElement(final Document document, final String identifier){
		return document.select(identifier).first();
	}

	public Elements fetchElementList(final Element element, final String identifier){
		return element.select(identifier);
	}

	public String extractHref(final Element hrefElement,final String identifier, final String attribute){
		return hrefElement.select(identifier).attr(attribute);
	}

	public String fetchDetails(final Document document, final String identifier){
		return fetchFirstElement(document, identifier).text();
	}

	public Money fetchUnitPrice(final Document document, final String identifier, final String attribute, final CurrencyUnit gbp){
		Element element = fetchFirstElement(document, identifier);
		element.select(attribute).remove();
		Double price = Double.parseDouble(element.text().substring(1));
		return Money.of(gbp, price);
	}

	public String fetchDocumentSize(final Document document, final String tempDetailsPageFileName) throws IOException{
		File tempFile = File.createTempFile(tempDetailsPageFileName, ".tmp"); 

		BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
		bw.write(document.outerHtml());
		bw.close();
		int fileSize = (int) tempFile.length();
		tempFile.delete();
		String fileSizeSting = getSizeHumanReadable(fileSize);
		return fileSizeSting;

	}

	private String getSizeHumanReadable(final int fileSize) {
		int unit = 1024;

		if (fileSize < unit) {
			return Integer.toString(fileSize) + " B";
		} else {
			int exp = (int) (Math.log(fileSize) / Math.log(unit));
			String pre = ("KMGTPE").charAt(exp - 1) + ("i");
			return String.format("%.1f %sB", fileSize / Math.pow(unit, exp), pre);
		}
	}
}
