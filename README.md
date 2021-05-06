# Web crawler
Application that crawl a web page and build site map in json with the links to other pages in the same domain.

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Execute:
Pass 3 parameters: url, destination file and max search deep, i.e.:
```
mvn clean install
mvn exec:java -Dexec.mainClass="BasicWebCrawler" -Dexec.args="https://liquid.tech/ ./pages.json 3"
```