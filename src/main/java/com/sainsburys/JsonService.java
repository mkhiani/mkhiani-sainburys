package com.sainsburys;

import java.util.LinkedList;

import org.joda.money.Money;
import org.json.simple.JSONObject;

public interface JsonService {
	public JSONObject toJSONObject(final LinkedList<Product> products, final Money total);
}
