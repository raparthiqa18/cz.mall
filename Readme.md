#**Project Name: MALL.CZ**

* This repository contains the UI testing project, using Cucumber + Selenium (Page Object Model)

* A simple scroll down to find the number of carousels and to validate the no. of elements in each carousels matches with the expected count. 

**Requirements**
* JDK 18
* Maven 3.8.6

**Test Cases**

*location: src/test/resources/TestCases.xlsx*

**Test Execution**
1. Download or clone the repository
2. Open a terminal
3. From the project root directory run: mvn clean test

**Configuration**

* By default, tests will be executed in Chrome (headless mode).
* Preferences can be changed in "config.properties" file
* These values can also be defined from the command line when launching the tests without the need of modify the properties file.

  *mvn test -Dbrowser=chrome -Dheadless=true*

**Results**
* To check the report, open the '/target/cucumber-reports/cucumber-html-reports/overview-features.html' file once the execution has finished.

**Reference Links**
* [Cucumber][1]
* [Gherkin][2]
* [Selenium][3] 
* [Page Object & Page Factory][4]

[1]: https://docs.cucumber.io/
[2]: https://cucumber.io/docs/gherkin/
[3]: https://www.selenium.dev/documentation/webdriver/
[4]: https://www.selenium.dev/documentation/test_practices/encouraged/page_object_models/
