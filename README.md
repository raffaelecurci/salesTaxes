salesTaxes
===========================

An application that writes and prints out the receipt details for shopping baskets

This example demonstrates:

* Output production as requested by hardcoding input inside test
* Unit tests written with [JUnit 4](http://junit.org/)
* Integration tests written with [JUnit 4](http://junit.org/)
* Code coverage reports via [Cobertura](http://cobertura.github.io/cobertura/)
* Javadoc Production via [MJAVADOC](http://maven.apache.org/plugins/maven-javadoc-plugin/)
* A Maven build that puts it all together

Running the tests
-----------------

* To run the unit tests, call `mvn test`
* To generate (unit test) code coverage reports, call `mvn cobertura:cobertura`, and point a browser at the output in `target/site/cobertura/`
* To generate application javadoc, call `javadoc:javadoc`, and point a browser at the output in `target/site/apidocs/`

* To view the build and tests results remotely point the browser at [https://travis-ci.org/raffaelecurci/salesTaxes]
Conventions
-----------

This example follows the following basic conventions:

 | unit test 
--- | --- | ---
__resides in:__ | `src/test/java/*Test.java` 
__executes in Maven phase:__ | test | verify
__handled by Maven plugin:__ | [surefire](http://maven.apache.org/surefire/maven-surefire-plugin/) 
