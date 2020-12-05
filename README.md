# auction-app-tests

## Introduction

This is a test automation project done as a part of the [Atlantbh](https://www.atlantbh.com/) internship. The tested app is the [Auction App](https://auction-app-test.netlify.app/).

## Setup instructions

1. Install Java JDK 11.0.8 and add environment variables to your system
2. Install [IntelliJ IDEA Community Edition](https://www.jetbrains.com/idea/download)
3. Install [Maven](https://downloads.apache.org/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.zip) and add environment variables to your system
4. Install the latest version of [Chrome](https://www.google.com/intl/en/chrome/)

 ## Verify installation
 
 In terminal, type:
 
 ```
 java -version
 ```
 ```
 mvn --v
 ```
 
 ## Running the tests

To run the tests go to the project folder, and type in terminal:

```
 mvn clean test -DsuiteXmlFile={suiteName}
 ```
example: mvn clean test -DsuiteXmlFile=smoke.xml.
