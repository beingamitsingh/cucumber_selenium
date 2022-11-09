**Description**
This repository contains automated tests for homework by SWEDBANK.

---

## Technology Stack Used
1. JAVA 8
2. Cucumber with TestNGCucumberRunner()
3. build.gradle is present
4. Extent Report has been used for reporting purposes. It contains a summarized dashboard & test logs.
5. selenium-java [version= 4.5.0]
6. Scripts have been tested on Google Chrome [version= 107.0.5304.87]

---

## Artifacts
Please go through this section to find more about the key artifacts used in the solution

1. Cucumber feature files have been included at **src\test\resources\features**
3. Test reports are located at **src\test\reports**
4. Object repository path = **src\test\resources\objects.xlsx**
5. Config.properties file path is **\src\test\resources\config.properties**

---

## Steps to execute the project
1. STEP 1: Clone the project from Github on local machine.
2. STEP 2: Import the solution into IntelliJ as gradle project.
3. STEP 3: Since tests are developed in Mac OS, it would be better to change all ooccurences of `/` to `\\`
4. STEP 3: Press "Run".

---

# Pre-requisites
1. Java 8 should be installed.

---

## Notes
Please keep in mind the following:

1. Test data has been handled via .feature files instead of an excel sheet due to time constraint.
2. However, the framework can be expanded to fulfill a more data-driven approach.
3. Provisions have been given to include screenshot of any failed steps/TCs.
4. The report folder contains the video recording of execution as well.

---
