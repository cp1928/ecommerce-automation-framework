# E-Commerce Automation Test Framework

An automated test framework built with **Selenium**, **Java**, and **TestNG**, using the Page Object Model (POM) design pattern, to test critical user journeys — Login, Cart, and Checkout — on [SauceDemo](https://www.saucedemo.com), a demo e-commerce site.

## Features

- Page Object Model (POM) architecture for maintainable, reusable code
- Cross-browser testing support (Chrome and Firefox)
- 40+ automated test cases covering login, cart, and checkout flows
- Explicit waits to handle dynamic page loads and reduce flakiness
- Automatic screenshot capture on test failure
- HTML test reports generated via ExtentReports
- Maven-based build and dependency management
- TestNG for test execution, grouping, and listeners

## Tech Stack

| Tool | Purpose |
|---|---|
| Java | Programming language |
| Selenium WebDriver | Browser automation |
| TestNG | Test execution framework |
| Maven | Build and dependency management |
| ExtentReports | HTML test reporting |
| Page Object Model | Design pattern for maintainable tests |

## Project Structure

```
src/
├── main/java/pages/        → Page Object classes (LoginPage, CartPage, CheckoutPage, ProductsPage)
└── test/java/
    ├── tests/               → Test classes (LoginTest, CartTest, CheckoutTest, ProductsPageTest)
    └── utils/               → Utilities (DriverFactory, ScreenshotUtil, ExtentManager, ExtentTestNGListener)
testng.xml                    → TestNG suite configuration
pom.xml                        → Maven dependencies and build configuration
```

## How to Run

1. Clone this repository:
```
git clone https://github.com/cp1928/ecommerce-automation-framework.git
```

2. Run all tests using Maven:
```
mvn test
```

3. Run tests on Firefox instead of Chrome:
```
mvn test -Dbrowser=firefox
```

4. View the HTML report after execution:
```
reports/ExtentReport.html
```

## Test Coverage

- **Login (10 tests):** valid/invalid credentials, empty fields, locked-out user, page title checks
- **Cart (10 tests):** add/remove items, badge count validation, price/name consistency, cart persistence
- **Checkout (11 tests):** valid checkout, missing field validation, cancel flows, order total, confirmation page
- **Products Page (8 tests):** sorting, product count, menu, logout, footer links

## Screenshots

### Test Execution in Console
![Test Execution](screenshots/test-execution.png)

## Author
Chitra P