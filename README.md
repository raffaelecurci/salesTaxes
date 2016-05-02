salesTaxes
===========================

An application that writes and prints out the receipt details for shopping baskets

This example demonstrates:

* Unit tests written with [JUnit 4](http://junit.org/)
* Integration tests written with [JUnit 4](http://junit.org/)
* Code coverage reports via [Cobertura](http://cobertura.github.io/cobertura/)
* Javadoc Production
* A Maven build that puts it all together

Running the tests
-----------------

* To run the unit tests, call `mvn test`
* To generate (unit test) code coverage reports, call `mvn cobertura:cobertura`, and point a browser at the output in `target/site/cobertura/`
* To generate application javadoc, call `javadoc:javadoc`, and point a browser at the output in `target/site/apidocs/`

Conventions
-----------

This example follows the following basic conventions:

 | unit test 
--- | --- | ---
__resides in:__ | `src/test/java/*Test.java` 
__executes in Maven phase:__ | test | verify
__handled by Maven plugin:__ | [surefire](http://maven.apache.org/surefire/maven-surefire-plugin/) 
