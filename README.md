# VHS Rental shop - Blast from the past

We are creating a cutting edge VHS rental application management system for our special clients that value the old time retro VHS experience. 

![img_1.png](backtothepast.png)

### VHS Rental Application

#### API
1. Implement a VHS resource RESTful API over http. 
Path to this resource should be: /api/vhs ✅
3. Implement CRUD RESTful API for Rental resource. 
Path to this resource should be: /api/rental ✅
#### JPA
- Model VHS as JPA Entity ✅
- Model User as JPA Entity ✅
- Model Rental as JPA Entity ✅
#### Requirements
- RentalController should accept needed user ID data, vhs ID data, and rental date(use form or PATH parameters) ✅ 
- Make sure that the same vhs can't have multiple rentals on the same date ✅
- handle rental due dates and late fees ✅
- 4 HTTP methods should be implemented. (e.g. GET, POST, PUT and DELETE) ✅
- Use Spring Data JPA Repositories ✅
- Use Slf4j Logback logging 
- Implement @ExceptionHandler to catch and handle all exceptions 
- Use Bean Validation on RentalForm to validate requests to RentalController ✅
- Customize error messages from REST controller with Message Source
- Prepopulate database of choice (H2, Postgresql or any other non-Oracle database)
- Create an automated test for functionality of choice 


#### UI or Postman collection
Create a simple postman collection for our VHS rental shop that will have these mandatory actions (put it in your git repository root folder):
- VHS
  - List
  - VHS Rent and Return option
- List of Rentals


# Guidance:


## Setup development environment
### Linux

1. Install Java JDK 14 or higher : http://jdk.java.net/14/
2. Install Maven 3.6.0 or higher: https://maven.apache.org/install.html
3. Install Git: https://www.atlassian.com/git/tutorials/install-git
4. Install IntelliJ IDEA CE: https://www.jetbrains.com/idea/download
5. Postman: https://www.postman.com/


### Windows

1. Install Java JDK 14 or higher: http://jdk.java.net/14/
2. Install Maven 3.6.3 or higher: https://maven.apache.org/install.html
3. Install Git: https://www.atlassian.com/git/tutorials/install-git
4. Install IntelliJ IDEA CE: https://www.jetbrains.com/idea/download
5. Postman: https://www.postman.com/


### Setup your account and repository
1. Setup GitHub account
2. Checkout repository with assignment
3. Fork your own repository
4. Create your own branch
5. Add your mentor as member to your repository with role Maintainer

### Spring Initializr

Visit the Spring Initializr to generate a new project with the required dependencies (Spring Web, Spring Data, ....).
https://spring.io/guides/gs/spring-boot/

### Books
- Spring Introduction https://www.baeldung.com/spring-intro
- https://start.spring.io/
- https://www.manning.com/books/spring-boot-in-action

