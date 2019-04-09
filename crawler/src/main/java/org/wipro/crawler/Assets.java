package org.wipro.crawler;

import java.util.Set;

public class Assets {
	private Set<String> sameDomainPages;
	private Set<String> externalDomainPages;
	private Set<String> images;
	
	public Set<String> getSameDomainPages() {
		return sameDomainPages;
	}
	public void setSameDomainPages(Set<String> sameDomainPages) {
		this.sameDomainPages = sameDomainPages;
	}
	public Set<String> getExternalDomainPages() {
		return externalDomainPages;
	}
	public void setExternalDomainPages(Set<String> externalDomainPages) {
		this.externalDomainPages = externalDomainPages;
	}
	public Set<String> getImages() {
		return images;
	}
	public void setImages(Set<String> images) {
		this.images = images;
	}
	
	
}
