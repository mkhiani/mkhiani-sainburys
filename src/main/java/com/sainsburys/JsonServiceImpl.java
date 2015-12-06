package com.sainsburys;

import java.util.LinkedList;

import org.joda.money.Money;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonServiceImpl implements JsonService{

	public JsonServiceImpl(){
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSONObject(LinkedList<Product> products, Money total) {
		JSONObject obj = new JSONObject();
		JSONArray results = new JSONArray();
		for (Product product: products) {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("title",product.getTitle());
			jsonObj.put("unit_price",product.getUnitPrice());
			jsonObj.put("size",product.getSize());	        	
			jsonObj.put("description", product.getDescription());
			results.add(jsonObj);
		}
		obj.put("results", results);
		obj.put("total", total.getAmount().toString());
		return obj;
	}
}
