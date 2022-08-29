**Name**
Rest-Assured TestNg Based Framework

**Introduction**
Presented Rest-Assured TestNg framework has been designed to test rest Apis .
There are following modules present in the framework
1.In Src/test/java, you will find the file called ‘GetEpochTimeConversionTest’ which will get the current date create a epoch time for the same , and hit the server and will verify the response time with the date/time
2.In the same module there is utility file called ‘ExcelReader’ which helps test to read the input data from excel sheet, before executing the test-cases
In resources directory there are 2 folders which are as following
3.Directory- Input Data - this has 2 input data files, which consists of Input test data for the test execution , this later can be improved to pass the data(data provider can be used for fetching the data and executing the test-cases based on the data)
4.Directory-properties – this has the properties file, which will be used to store the server details and user credentials information.
5.testng.xml file – this is a configuration file, which helps us to organize our tests.
6.Directory test-output – this will have the HTML Based test execution report.

**How To Execute the Tests?**
You can execute the tests by accessing the testing.xml file, where end user can configure the tests based on the requirements.

**How to Install the framework?**
You need to have JDK , maven and IntelliJ installed in your machine .
Since it’s a maven based framework , once user has maven installed and setup (path) ,by running the command like ‘mvn clean install ‘, users can build the project , maven  will get all the dependencies required for the project on your local machine.

