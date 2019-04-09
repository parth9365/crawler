package org.wipro.crawler;

import java.net.MalformedURLException;
import java.util.Set;

import static spark.Spark.get;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Crawler App
 *
 */
public class App {

	private static ObjectMapper om = new ObjectMapper();

	public static void main(String[] args) throws MalformedURLException {
		
		//define the URL to crawl 
		String URL = "https://wiprodigital.com/";
		
		//initialize the crawler object with the specified URL
		Crawler crawler = new Crawler(URL);
		
		
		//Assets are the list of different types of URL. eg. Same domain or External domain or images etc..
		Assets assets = new Assets();
		assets.setSameDomainPages(crawler.getSameDomainPages());
		assets.setExternalDomainPages(crawler.getExternalDomainPages());
		assets.setImages(crawler.getImages());

		//by this point, application seems to be initialized and should have crawled the URL
		System.out.println("Application initialized. \nhttp://localhost:4567/crawl ");
		
		
		//GET endpoint to get the crawled data
		get("/crawl", (request, response) -> {
			Set<String> result = crawler.getSameDomainPages();
			if (result.isEmpty()) {
				return om.writeValueAsString("nothing found");
			} else {
				return om.writeValueAsString(assets);
			}
		});

	}
}
