### Master

[![Build Status](https://github.com/santander-group-ems-gln/ems-mportal-merchant-portal/actions

### Develop

[![Build Status](https://github.com/santander-group-ems-gln/ems-mportal-merchant-portal/actions

| Statements                                                                                 | Branches                                                                             | Functions                                                                                | Lines                                                                            |
| ------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------ | ---------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------- |
| ![Statements](https://img.shields.io/badge/statements-100%25-brightgreen.svg?style=flat) | ![Branches](https://img.shields.io/badge/branches-100%25-brightgreen.svg?style=flat) | ![Functions](https://img.shields.io/badge/functions-100%25-brightgreen.svg?style=flat) | ![Lines](https://img.shields.io/badge/lines-100%25-brightgreen.svg?style=flat) |

## Introduction

Example for use Spring Boot Java.

## Wiki

**Check the wiki for more information of implementations** [Not yet]().

### Running on native machine

1. Install JDK 1.17
2. Install Gradle 4+ or Maven 3.2+ 
3. You can also import the code straight into your IDE:
    * Spring Tool Suite (STS)
    * IntelliJ IDEA
4. **Generate SSL Certificates (First Time Only):**
   ```powershell
   .\generate-ssl-certs.ps1
   ```
   This script automatically detects your Java installation and creates self-signed certificates (`springboot.p12` and `springboot.jks`) in `src/main/resources/` with password `123456`. Required for HTTPS support on localhost.

5. Set the environment local=true in your favorite IDE
<br>

   ![img.png](https://dev.azure.com/albatross-getnet/1e4efc26-8735-4478-92a6-efeb2a64588e/_apis/git/repositories/c7e2540a-6ae7-4ce3-b5d5-3a3c3d9c0c2b/Items?path=/.attachments/img-39394989-c317-4b53-a246-aeef66e08f7c.png&download=false&resolveLfs=true&%24format=octetStream&api-version=5.0-preview.1&sanitize=true&versionDescriptor.version=wikiMaster)
<br>
  ![img_1.png](https://dev.azure.com/albatross-getnet/1e4efc26-8735-4478-92a6-efeb2a64588e/_apis/git/repositories/c7e2540a-6ae7-4ce3-b5d5-3a3c3d9c0c2b/Items?path=/.attachments/img_1-1e4a4e76-9b13-42a6-9581-99f6646ce8c3.png&download=false&resolveLfs=true&%24format=octetStream&api-version=5.0-preview.1&sanitize=true&versionDescriptor.version=wikiMaster)

<br>

6. Run the command `.\mvnw spring-boot:run`
7. Access `https://localhost:8080/api/docs` and you're ready to go!
   
   **Note:** If you get SSL errors, make sure you ran the certificate generation script in step 4.

### Running on docker engine

1. Comming soon

## Models

The models is necessary to input (reader) and output (writer) Java Objects.

## Scripts

This boilerplate comes with a collection of Maven scripts to make your life easier, you'll run them with `.\mvnw <script name>`:

- `spring-boot:run`: Run the application
- `clean package` Run the generate .jar file to run with the command 

## Tech

- [Java v17+](https://www.oracle.com/java/technologies/javase/17u-relnotes.html)
- [Maven v3.6.0](https://maven.apache.org/docs/3.6.0/release-notes.html)
