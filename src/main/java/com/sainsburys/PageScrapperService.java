package com.sainsburys;

import java.io.IOException;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public interface PageScrapperService {
	Document fetchDocument(final String httpAddress) throws IOException;

	Element fetchFirstElement(final Document document, final String identifier);


	Elements fetchElementList(final Element element, final String identifier);


	String extractHref(final Element hrefElement,final String identifier, final String attribute);


	String fetchDetails(final Document document, final String identifier);

	Money fetchUnitPrice(final Document document, final String identifier, final String attribute, final CurrencyUnit gbp);

	String fetchDocumentSize(final Document document, final String tempDetailsPageFileName) throws IOException;
}
