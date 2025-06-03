# 🧪 SauceDemo Automation Testing

A small automation testing project built to test key functionalities of the [SauceDemo](https://www.saucedemo.com/) website.

## 🔍 Project Overview

This project aims to test core features of the SauceDemo web application using **Selenium WebDriver**, **JUnit 5**, and the **Page Object Model (POM)** structure.  
It serves as part of my portfolio to demonstrate automation testing skills.

## ✅ Features Covered

- Login with valid and invalid credentials
- Add products to cart
- Remove products from cart
- Checkout process
- Edge case handling (e.g., checkout with special characters, empty cart)

## 🚩 Failed Test Scenarios (Detected Issues)

| ID | Description |
|----|-------------|
| TC-018 | System allows placing an order with an empty cart |
| TC-019 | Checkout process accepts special characters in customer name fields |

## 📁 Project Structure
```
src/test/java/
├── base/ # Base test class with common setup/teardown logic
├── pages/ # Page Object classes for UI abstraction
├── tests/ # JUnit test classes for test scenarios
└── utils/ # WebDriver setup and helper utilities
testcases/
└── SDTestcase.xlsx # List of test cases
```

## 🛠️ Technologies Used

- Java
- Selenium WebDriver
- JUnit 5
- WebDriverManager
- Eclipse

## 📄 Test Cases

All test cases are available in the [`/testcases`](./testcases) folder as an `.xlsx` file.

| ID | Test Case Description                                            | Status |
|----|------------------------------------------------------------------|--------|
| TC-001 | Login with valid credentials                                 | ✅ Pass |
| TC-002 | Login with username contains special characters              | ✅ Pass |
| TC-003 | Login with blank username                                    | ✅ Pass |
| TC-004 | Login with blank password                                    | ✅ Pass |
| ...    | ...                                                          | ...    |

## ▶️ How to Run

1. Clone the repository  
   `git clone https://github.com/nqc3802/SauceDemo.git`

2. Open in Eclipse or IntelliJ

3. Run test classes located in `src/tests/` using JUnit

> **Note:** WebDriverManager automatically handles the correct driver version.

## 📬 Contact

**Nguyen Quang Chinh**  
Email: nqc3822@gmail.com  
LinkedIn: [*nqc3802*](https://www.linkedin.com/in/nqc3802/)

---


