
package org.wipro.crawler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {
	private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36";
	private Queue<String> links;
	private String domain;
	private Set<String> set;
	private Set<String> sameDomainPages = new HashSet<>();
	private Set<String> externalDomainPages = new HashSet<>();
	private Set<String> images = new HashSet<>();
	

	public Crawler(String URL) throws MalformedURLException {
		links = new LinkedList<>();
		set = new HashSet<>();
		
		//crawl the URL when initializing the crawler object and keep it in the memory (cached)  
		//so it can serve the request fast 
		System.out.println("-----------------Crawling for URL " + URL + " started-----------------" );
		getPageLinks(URL);
		System.out.println("-----------------Crawling for URL " + URL + " completed-----------------" );
	}
	
	// This method basically applies BFS to crawl the URL
	// It adds the new URL found in a page at the end of the Queue and keeps searching for it. 
	private void getPageLinks(String URL) {
		try {
			this.domain = getDomainName(URL);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		
		//seed the first URL received in argument
		links.add(processURL(URL));
		
		
		//keep crawling until the end of queue
		while(!links.isEmpty()) {
			
			//fetch the URL out of the queue and search for it. 
			String link = links.poll();
			
			System.out.println("Crawling URL: " + link);
			
			// keeps list of all the URLs visited till now
			set.add(link);
			
			try {
				if (link.contains(this.domain)) {
					
					//initialize the document object
					Document document = Jsoup.connect(link).userAgent(USER_AGENT).get();
					
					//get all the URLs
					Elements otherLinks = document.select("a");
					
					//get all images
					Elements images = document.select("img[src~=(?i)\\.(png|jpe?g|gif)]");  

					//fetch all the  links in the page
					for (Element page : otherLinks) {
						String absoluteURL = processURL(page.attr("abs:href"));
						
						if (!links.contains(absoluteURL) && !link.equalsIgnoreCase(absoluteURL) && !set.contains(absoluteURL)) {
							
							//queue the new found URL and visit it later 
							links.add(absoluteURL);
							
							//System.out.println(absoluteURL);
							//differentiate URL as same domain or external
							if(absoluteURL.contains(domain)) {
								sameDomainPages.add(absoluteURL);
							} else { 
								externalDomainPages.add(absoluteURL);
							}
						}
					}
					
					//fetch all images
					for(Element image: images) {
						this.images.add(image.attr("src"));
					}
				}
			} catch (IOException e) {
				System.out.println("Exception: " + e.getMessage() + " occurred while fetching URL: " + link );
//				e.printStackTrace();
			}
		}
	}

	/**
	 * This method get the domain from the URL specified in the parameter
	 * @param url
	 * @return domain
	 * @throws MalformedURLException
	 */
	private static String getDomainName(String url) throws MalformedURLException {
		URL url1 = new URL(url);
		return url1.getProtocol() + "://" + url1.getHost();
	}
	
	
	/**
	 * This method process the URL and removes the query parameters or hash values 
	 * @param theURL
	 * @return URL without query params
	 */
	private String processURL(String theURL) {
	    int endPos;
	    if (theURL.indexOf("?") > 0) {
	        endPos = theURL.indexOf("?");
	    } else if (theURL.indexOf("#") > 0) {
	        endPos = theURL.indexOf("#");
	    } else {
	        endPos = theURL.length();
	    }

	    return theURL.substring(0, endPos);
	}
	
	
	/**
	 * Getter method for the same domain pages 
	 * @return Set of same domain pages 
	 */
	public Set<String> getSameDomainPages() {
		return sameDomainPages;
	}
	
	/** 
	 * Getter method for the external domain pages referenced in a page 
	 * @return Set of external domain pages  
	 */
	public Set<String> getExternalDomainPages() {
		return externalDomainPages;
	}
	
	
	/**
	 * Getter method for the images in the page
	 * @return Set of images in the page 
	 */
	public Set<String> getImages() {
		return images;
	}

}