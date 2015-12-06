package com.sainsburys;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.joda.money.CurrencyUnit;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PageScapperServiceTest {

		private static final String NON_EXISTENT_URL = "http://www.thisdoesnotexixts.com";
		private static final String URL = "http://www.google.com";
		private static final String TEST_STRING = "string";
		private static final String NO_ELEMENT = "";
		
		@InjectMocks
		private PageScrapperService pageScrapperService = new PageScrapperServiceImpl();

		@Mock
		private Elements elements;

		@Mock
		private Element element;

		@Mock
		private Document document;

		@Mock
		private Jsoup jsoup;

		@Before
		public void setUp()
		{
			when(document.select(TEST_STRING)).thenReturn(elements);
			when(document.select(TEST_STRING).first()).thenReturn(element);
			when(element.select(TEST_STRING)).thenReturn(elements);
			when(document.select(TEST_STRING).first().text()).thenReturn(TEST_STRING);
			when(elements.attr(TEST_STRING)).thenReturn("href");
		}


		@Test(expected=IllegalArgumentException.class)
		public void testNullURL() throws Exception {
			pageScrapperService.fetchDocument(null);
		}

		@Test(expected=IOException.class)
		public void testIOException() throws IOException {
			pageScrapperService.fetchDocument(NON_EXISTENT_URL);
		}

		@Test
		public void testFetchDocument() throws IOException{
			assertNotNull(pageScrapperService.fetchDocument(URL));
		}

		@Test
		public void testFetchFirstElement(){
			assertNotNull(pageScrapperService.fetchFirstElement(document, TEST_STRING));
		}

		@Test(expected = NullPointerException.class)
		public void testFetchFirstElementNotFound(){
			pageScrapperService.fetchFirstElement(document, NO_ELEMENT);
		}
		
		@Test
		public void testFetchElementList(){
			assertNotNull(pageScrapperService.fetchElementList(document, TEST_STRING));
		}

		@Test(expected = NullPointerException.class)
		public void testFetchElementListNotFound(){
			assertEquals(pageScrapperService.fetchElementList(document, NO_ELEMENT).size(), 10);
		}

		@Test
		public void testExtractHref(){
			assertNotNull(pageScrapperService.extractHref(element, TEST_STRING, TEST_STRING));
		}

		@Test
		public void testFetchDetails(){
			assertNotNull(pageScrapperService.fetchDetails(document, TEST_STRING));
		}

		@Test
		public void testFetchUnitPrice(){
			when(element.text().substring(1)).thenReturn("100");
			assertNotNull(pageScrapperService.fetchUnitPrice(document, TEST_STRING, TEST_STRING, CurrencyUnit.GBP));
		}

		@Test
		public void testFetchDocumentSize() throws IOException{
			when(document.outerHtml()).thenReturn("SOME TEXT");
			assertNotNull(pageScrapperService.fetchDocumentSize(document, TEST_STRING));
		}
}
