package org.wipro.crawler;

import java.net.MalformedURLException;

import org.junit.Test;

import junit.framework.TestCase;

public class CrawlerTest extends TestCase {

	Crawler crawl;
	public CrawlerTest(String testName) throws MalformedURLException {
		super(testName);
		crawl = new Crawler("https://wiprodigital.com/");
	}


	@Test
	public void testSameDomainPagesIsNotNull() {
		assertNotNull(crawl.getSameDomainPages());
	}
	
	@Test
	public void testExternalDomainPagesIsNotNull() {
		assertNotNull(crawl.getExternalDomainPages());
	}

	@Test
	public void testImagesIsNotNull() {
		assertNotNull(crawl.getExternalDomainPages());
	}
}
