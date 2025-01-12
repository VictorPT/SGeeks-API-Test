# SGeeks-API-Test
API Test Project

## Overview
This project is a modular RestAssured-based framework for testing APIs. It includes validations for endpoint responses.

## Features
- Modular and reusable helper methods.
- Validations for response status codes, field values, and data types.
- Extent reports for test results.

## Setup

### Prerequisites
- Java 11+
- Maven 3.8+

### Steps
1. Clone the repository:
git clone https://github.com/VictorPT/SGeeks-API-Test.git

2. Install dependencies:
mvn clean install

3. Run the tests:
mvn test

## Assumptions and Limitations
- Tests assume JSON responses.

## Reporting
- Type `start target/extentReport.html` to view the report. 
- Report is generated in the same path `target/extentReport.html`
