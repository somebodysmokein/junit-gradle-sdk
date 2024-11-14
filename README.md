
<h1 align="center">   :zap: <img src="https://avatars.githubusercontent.com/u/1119453?s=200&v=4" width="60" height="60" > <a href="https://github.com/browserstack/browserstack-examples-junit5">BrowserStack Examples JUnit 5</a>  <img src="https://camo.githubusercontent.com/abbaedce4b226ea68b0fd43521472b0b146d5ed57956116f69752f43e7ddd7d8/68747470733a2f2f6a756e69742e6f72672f6a756e6974352f6173736574732f696d672f6a756e6974352d6c6f676f2e706e67" width="60" height="60" >
 :zap:</h1>

 # :label: [Introduction](https://github.com/browserstack/browserstack-examples-junit5#introduction) 

Welcome to BrowserStack JUnit 5 Examples, a sample UI testing framework empowered with **[Selenium](https://www.selenium.dev/)** and **[JUnit 5](https://junit.org/junit5/)**. Along with the framework the repository also contains a collection of sample test scripts written for **[BrowserStack Demo Application](https://bstackdemo.com/)**.

This repository includes a number of **[sample configuration files](/src/test/resources)** to run these on tests on various platforms including **on-premise browsers**, browsers running on a remote selenium grid such as **[BrowserStack Automate](https://www.browserstack.com/automate)**. The framework and test scripts are configured to run with both **[Gradle](https://gradle.org/)** and **[Maven](https://maven.apache.org/)**. Starter **[gradle.build](/build.gradle)** and **[pom.xml](/pom.xml)** files are also included in the project.

## Repository setup

- Clone the repository

- For this infrastructure configuration (i.e on-premise), create the `drivers` folder at `/src/test/resources` and ensure that the ChromeDriver executable is placed in the `/src/test/resources/drivers` folder.

- Ensure you have the following dependencies installed on the machine
  - Java >= 8
  - Maven >= 3.1+
  - Gradle >= 5.0+

  Maven:
    ```sh
    mvn clean install
    ```

## About the tests in this repository

This repository contains the following Selenium tests:

| Module   | Test name                          | Description |
  | ---   | ---                                   | --- |
| E2E      | Regression                | This test scenario verifies successful product purchase lifecycle end-to-end. It demonstrates the [Page Object Model design pattern](https://www.browserstack.com/guide/page-object-model-in-selenium) and is also the default test executed in all the single test run profiles. |
| LocalTest    | BStackLocalTest          | This test verifies the workflow of locally hosted webistes, by navigating to http://localhost:45454/ url |
| Login    | NavigateToLoginPage          | This test verifies the workflow of navigating to Login Page |
| Login    | LockedUserTest              | This test verifies the login workflow error for a locked user. |
| Offers   | CheckOffers     | This test mocks the GPS location for Mumbai and verifies that the product offers applicable for the Mumbai location are shown.   |
| Product  | ApplyingSamsungAndAppleFilter          | This test verifies that Apple and Samsung products are only shown by applying Samsung and Apple vendor filter. |
| Product  | SortLowestToHighest   | This test verifies that the product prices are in ascending order when the product sort "Lowest to Highest" is applied. |
| User     | ImageNotLoading | This test verifies that the product images load for user: "image_not_loading_user" on the e-commerce application. Since the images do not load, the test case assertion fails.|
| User     | CheckExistingOrders |  This test verifies that existing orders are shown for user: "existing_orders_user"  |
| User     | CheckFavourites |  This test verifies that Favourites items are shown for user:  "fav_user"|
  
---



## Test infrastructure environments

- [On-premise/self-hosted](#on-premise-self-hosted)
- [BrowserStack](#browserstack)


## Configuring the maximum parallel test threads for this repository

For all the parallel run configuration profiles, you can configure the maximum parallel test threads by changing the settings below.


- BrowserStack

    Navigate to [capabilities-single.yml](capabilities-single.yml)
    Set the required number of parallelsPerPlatform to a preferred number, for eg: parallelsPerPlatform = 5
    You can even update the parallelsPerPlatform for other capability profiles. 


# On Premise / Self Hosted

This infrastructure points to running the tests on your own machine using a browser (e.g. Chrome) using the browser's driver executables (e.g. ChromeDriver for Chrome). Selenium enables this functionality using WebDriver for many popular browsers.

## Prerequisites

- For this infrastructure configuration (i.e on-premise), create the `drivers` folder at `/src/test/resources` and ensure that the ChromeDriver executable is placed in the `/src/test/resources/drivers` folder.

Note: The ChromeDriver version must match the Chrome browser version on your machine.

## Running Your Tests

### Run a specific test on your own machine

- How to run the test?

  To run the default test scenario (e.g. End to End Scenario) on your own machine, use the following command:

  Maven:
    ```sh
  mvn test -P on-prem
  ```
  To run the entire suite on-prem, use the following command:

  Maven:
    ```sh
  mvn test -P on-prem-suite
  ```

- Output

  This run profile executes a specific test scenario on a single browser instance on your own machine.


# BrowserStack

[BrowserStack](https://browserstack.com) provides instant access to 3,000+ real mobile devices and browsers on a highly reliable cloud infrastructure that effortlessly scales as testing needs grow.

## Prerequisites

- Create a new [BrowserStack account](https://www.browserstack.com/users/sign_up) or use an existing one.
- Identify your BrowserStack username and access key from the [BrowserStack Automate Dashboard](https://automate.browserstack.com/) and export them as environment variables using the below commands.

  - For \*nix based and Mac machines:

  ```sh
  export BROWSERSTACK_USERNAME=<browserstack-username> &&
  export BROWSERSTACK_ACCESS_KEY=<browserstack-access-key>
  ```

  - For Windows:

  ```shell
  set BROWSERSTACK_USERNAME=<browserstack-username>
  set BROWSERSTACK_ACCESS_KEY=<browserstack-access-key>
  ```

  Alternatively, you can also hardcode username and access_key objects in the browserstack.yml file.

Note:
- We have configured a list of test capabilities in the [capabilities-single.yml](capabilities-single.yml) and in the other capability config files. You can certainly update them based on your device / browser test requirements.
- The exact test capability values can be easily identified using the [Browserstack Capability Generator](https://browserstack.com/automate/capabilities)


## Running Your Tests

### Run a specific test on BrowserStack

In this section, we will run a single test on Chrome browser on Browserstack. To change test capabilities for this configuration, please refer to the [capabilities-single.yml](capabilities-single.yml)

- How to run the test?

  - To run the default test scenario (e.g. End to End Scenario) on your own machine, use the following command:

  Maven:
  ```sh
  mvn test -P bstack-single
  ```


- Output

  This run profile executes a single test on a single browser on BrowserStack. Please refer to your [BrowserStack dashboard](https://automate.browserstack.com/) for test results.


### Run the entire test suite in parallel on multiple BrowserStack browsers

In this section, we will run the tests in parallel on multiple browsers on Browserstack. Refer to the [capabilities-parallel.yml](capabilities-parallel.yml) file to change test capabilities for this configuration.

- How to run the test?

  To run the entire test suite in parallel on multiple BrowserStack browsers, use the following command:

  Maven:
  ```sh
  mvn test -P bstack-parallel
  ```

### [Web application hosted on internal environment] Running your tests on BrowserStack using BrowserStackLocal

- How to run the test?

  - Set browserStackLocal to true in `capabilities-local.yml` file
  - To run the a local testing scenario (e.g. refer BStackLocalTest) on a single BrowserStack browser using BrowserStackLocal, use the following command:

  Maven:
  ```sh
  mvn test -P bstack-local
  ```

- Output

  This run profile executes a single test on an internally hosted web application on a single browser on BrowserStack. Please refer to your BrowserStack dashboard(https://automate.browserstack.com/) for test results.

## Generating Reports
- Using [Test Observability](https://www.browserstack.com/docs/test-observability/overview/what-is-test-observability)
- When you run the maven commands, XML reports will be generated in the target/surefire-reports directory of this project. You can upload these JUnit XML reports into Test Observability by making a POST request to the designated upload API.
- Follow this [help-doc](https://www.browserstack.com/docs/test-observability/quick-start/junit-reports#integrate-with-test-observability-using-junit-reports) to import your first JUnit report into Test Observability. 


## Additional Resources
- Test Observability by BrowserStack: A platform that replaces all test reporting & debugging tools, follow this [link](https://www.browserstack.com/test-observability) to learn more.
- View your test results on the [BrowserStack Automate dashboard](https://www.browserstack.com/automate)
- Documentation for writing [Automate test scripts in Java](https://www.browserstack.com/automate/java)
- Customizing your tests capabilities on BrowserStack using our [test capability generator](https://www.browserstack.com/automate/capabilities)
- [List of Browsers & mobile devices](https://www.browserstack.com/list-of-browsers-and-platforms?product=automate) for automation testing on BrowserStack
- [Using Automate REST API](https://www.browserstack.com/automate/rest-api) to access information about your tests via the command-line interface
- Understand how many parallel sessions you need by using our [Parallel Test Calculator](https://www.browserstack.com/automate/parallel-calculator?ref=github)
- For testing public web applications behind IP restriction, [Inbound IP Whitelisting](https://www.browserstack.com/local-testing/inbound-ip-whitelisting) can be enabled with the [BrowserStack Enterprise](https://www.browserstack.com/enterprise) offering


[comment]: <> (## Open Issues)

[comment]: <> (<Placeholder section for any known open issues &#40;some test known to not work or is flaky&#41;. If none, please remove the section>)
