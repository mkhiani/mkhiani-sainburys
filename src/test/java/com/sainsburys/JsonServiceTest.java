package com.sainsburys;

import static org.junit.Assert.assertNotNull;
import static org.joda.money.CurrencyUnit.GBP;

import java.util.LinkedList;

import org.joda.money.Money;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;

public class JsonServiceTest {
	
	private final JsonService jsonService = new JsonServiceImpl();
	private Product product;
	private LinkedList<Product> products;
	private Money total;
	JSONObject jsonObject;
	
	@Before
	public void setup()
	{
		products = new LinkedList<Product>();
		product = new Product();
		product.setTitle("Title");
		product.setUnitPrice(Money.of(GBP, 100L));
		product.setDescription("Fruit Description");
		product.setSize("100");
		products.add(product);
		total = Money.total(GBP, Money.zero(GBP));
	}
	

	@Test
	public void testJSONObject()
	{
		 JSONObject jsonObject = jsonService.toJSONObject(products, total);
		 assertNotNull(jsonObject);
	}
}
