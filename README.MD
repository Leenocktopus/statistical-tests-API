## Back end for Statistical Tests web application

#### General
This project is basically a tool to connect [JavaScript application](https://github.com/Leenocktopus/statistical-tests-webapp) 
to the [library of statistical tests](https://github.com/Leenocktopus/random-bit-sequence-test). 

Technologies used: *Spring Boot, JUnit*.

#### Endpoints
According to the requirements, there are endpoints for both, 
standalone and group tests. 
The POST method is used to perform all available tests.
Other methods aren't supported because there is no need for them. 

Information about the endpoints aggregated in the following table:

|test|URL|
|---|---| 
|Standalone NIST test|/nist_test/{test}| 
|Group of NIST tests|/nist_test| 
|Standalone multidimensional test|/md_test/{test}| 
|Group of multidimensional tests|/md_test?tests=[test1, test2...]| 
