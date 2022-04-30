# Simple Crawler Web App
## Description

it's simple web crawler in Spring Boot. The crawler should be limited to one domain - so when you start with https://monzo.com/, it would crawl all pages within monzo.com, but not follow external links, for example to the Facebook and Twitter accounts. Given a URL, it should print a simple site map, showing the links between pages.

 
 ### Technology
* Programming Language Java (version 8 and above)
* Remote Service Protocol REST
* Testing/ Stubbing Junit, Mockito
* Build Maven
*Framework: Spring Boot
*Database: MySQL
*Docker
*jsoup
*caching

	
## Test Coverage

### Using IDE
* make sure that java version >= java 8 is installed
* clone Repository : https://github.com/EslamElkhafagy/web-crawler
* make sure that maven is installed and run mvn clean install
* install mysql database
* create crawler schema (CREATE SCHEMA `crawler` DEFAULT CHARACTER SET utf8 ;) 
* update user&pass at aplication.yml file
* Running the Project

## Test Coverage

* ![alt text](https://github.com/EslamElkhafagy/web-crawler/blob/main/test-coverage.png?raw=true)
