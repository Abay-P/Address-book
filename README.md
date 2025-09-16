# Address-book
**Address-book** application for view, add, update and delete contacts.
Available info in contacts include:
1. [x] First name
2. [x] Last name
3. [x] Birthdate
4. [x] Street
5. [x] City
6. [x] Pin code,
7. [x] House no
8. [x] Address type
9. [x] Phone no
10. [x] phone type

### **Technical Info** 

* Language: kotlin
* Framework: Spring boot version 3
* Data base: Postgres
* ORM: Hibernate with JPA
* UI: CommandLiner interface
* Migration: Liquibase
* Testing: JUnit 5

### Configurations
* Clone the Git Repository
   git clone https://github.com/Abay-P/address-book.git
   cd address-book-cli
* Install PostgreSQL on your system:
   Windows: Download from https://www.postgresql.org/download/windows/
* Start PostgreSQL
* Create database
   CREATE DATABASE addressbook;

   Create user
   CREATE USER abaykdas WITH ENCRYPTED PASSWORD 'password123';

   Grant privileges
   GRANT ALL PRIVILEGES ON DATABASE addressbook TO abaykdas;
* Open in Intellij, and run the application
* User can add, update, delete, view contact using the commands in command liner interface

### User-level Instructions

User need to enter commands as per the given instructions in command line interface, according to the actions needed for them. In any case user can exit using the 'exit' command. Provide appropriate command and required fields may not be null.