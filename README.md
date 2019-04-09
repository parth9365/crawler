# crawler
This package provides a class to crawl links on a website. 

Mainly uses <a href="https://jsoup.org/">jsoup</a> to extract data from the HTML. 

Uses <a href="http://sparkjava.com/">Spark Java</a> to expose the /crawl webservice. 

<a href="https://junit.org/"> JUnit </a> for test cases. 

<h2> Installation </h2>

This package can be installed via following below steps.

<ol>
	<li> Download <a href="https://github.com/parth9365/crawler/">this</a> repository </li> 
  <li> Specify the URL you want to crawl in <code>org.wipro.crawler.App.java</code>. <br/>Default URL is specified as <a href="https://wiprodigital.com/"> https://wiprodigital.com </a>
	<li> Go to crawler directory and run below maven command <br/> <code>clean compile assembly:single</code></li>
  <li> Above step should generate a <code>crawler-0.0.1-SNAPSHOT-jar-with-dependencies.jar</code> in project's target directory </li>
  <li> Run this generated jar with java as below: <br/> <code>java -jar crawler-0.0.1-SNAPSHOT-jar-with-dependencies.jar</code>
  <li> It should crawl the specified URL in <code> App.java. This step may take some time to crawl all the found pages. </code>
  <li> Once it completes crawling, server will start on <code>localhost:4567</code> serving the enpoint <code>/crawl</code>
  <li> Service can be accessed at: <code>http://localhost:4567/crawl</code>
</ol>

Feel free to contact me <code>parth.9365 [at] gmail [dot] com</code>
