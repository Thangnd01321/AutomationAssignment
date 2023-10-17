# AutomationAssignment
Automation Testing Practical Assignment

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

Make sure you have installed all of the following prerequisites on your development machine:
* JDK 14 - [Download and install JDK 14](https://www.oracle.com/java/technologies/javase/jdk14-archive-downloads.html)
* Maven - [Download & Install Maven](http://maven.apache.org/)
* Chrome browser
* Firefox browser
* Microsoft Edge browser

## Test Configuration

### UI Automation Test

#### Run Test
From your terminal/command prompt, change directory to folder where you clone the project and run following command. 
```
mvn clean test -Dsurefire.suiteXmlFiles=testconf/web/assignment.xml -Dheadless=true
```
This command will run the test in headless mode with browser <b>CHROME</b><br>

#### Support different browsers
You can run on different browsers (Microsoft edge, chrome, firefox)
```
mvn clean test -Dsurefire.suiteXmlFiles=testconf/web/assignment.xml -Dheadless=true -Dbrowser=firefox
```
```
mvn clean test -Dsurefire.suiteXmlFiles=testconf/web/assignment.xml -Dheadless=true -Dbrowser=edge
```
After the test finishes, you can find the html report `target\cucumber-html-reports\overview-features.html`

#### Run test with UI
If you want to see how it works on UI, Open file `testconf\web\assignment.xml` and change value of `thread-count="4"` to `thread-count="1"` and run command
```
mvn clean test -Dsurefire.suiteXmlFiles=testconf/web/assignment.xml
```
#### Video recording
By default, the test will capture a screenshot at the end of each test case and attachs into html report. Beside of that, it also supports video recording. To enable this option
* Open file `testconf\web\assignment.xml` and change value of `thread-count="4"` to `thread-count="1"`.
* you can use parameter `recording` with value `true`.
```
mvn clean test -Dsurefire.suiteXmlFiles=testconf/web/assignment.xml -Drecording=true
```
Video recording of each test case will be attached into html report 


### API Automation Test

#### Test Configuration
From your terminal/command prompt, change directory to folder where you clone the project and run following command. 
```
mvn clean test -Dsurefire.suiteXmlFiles=testconf/api/assignment.xml
```
After the test finishes, you can find the html report `target\cucumber-html-reports\overview-features.html`

