package com.sainsburys;

import org.json.simple.JSONObject;

public class SainsburyApp {
	public static void main(String[] args) {
		try {
			PageScrapper pageScrapper = new PageScrapper();

			JsonService jsonService = new JsonServiceImpl();
			JSONObject jsonObject =  jsonService.toJSONObject(pageScrapper.fetchWebData(), pageScrapper.getTotal());

			System.out.println(jsonObject.toJSONString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
