# Sainsbury's Webpage to JSON
by Alex Jollands, Friday 21st April 2017


### Overview
This is a lean Maven project, with source Java files, tests and an executable JAR.
It is a console application only - calling the main method will output the required JSON snippet.

### How to run
#### System requirements
* Java 8
* Maven
   1. Download binary zip from http://apache.mirrors.nublue.co.uk/maven/maven-3/3.5.0/binaries/apache-maven-3.5.0-bin.zip
   2. Unpack and add root folder to path.
   3. Test using 'mvn --version'

#### Setup
In the repository root, use 'mvn install' to build the project.

#### Running Tests
In the repository root, use 'mvn test'.

#### Running Application
In the /target folder, use 'java -jar target/webpage-to-json-1.0-SNAPSHOT.jar' to execute the JAR.


### Dependencies
JUnit
Gson - Google JSON utility
JSoup - HTML parsing library. MIT licensed. No external dependencies.


### Notes
Known issues:
Greater error handling is needed, for example null checks, empty array checks (prior to calling first() method) etc.
Code structure could be improved for maintainability, for example extracting css selectors out to a single config file or static strings at the top of the class.
Testing coverage is low. Unit tests included here to demonstrate competency, recognising limits in coverage and general application development due to time constraints (trying to keep to the recommended 2 hours). 


### Further work
For an example of a completed code project, please see my Visualising an Ant Colony Optimisation Algorithm app, written in Javascript -
Repository: https://github.com/alexjollands/VisAcoDev
Information: https://arj24.wordpress.com/