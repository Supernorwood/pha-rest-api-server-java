# PHA Form A Data Intake API [Project Summary]

## About This Project

The PHA Form A Data Intake API is a Spring Web based RESTful API.

The RESTful API performs data loading, data validation, and data transformation operations on key user demographic data from PHA Form A.

The aim of this project is to create a RESTful API server to accept and transform PHA Form A data.

## About The Form A Data Intake API

The 'PHA Form A' Data Intake API exposes a RESTful POST endpoint similar to the following:

`/pha/person/transform-user-data`

This endpoint parses and extracts key user information submitted to the API over POST requests.

## Project Objectives:

### Data Intake API: Phase 1 Objectives

Phase 1 of the PHA Form A Data Intake API shall expose the following capabilities:

- Expose a POST endpoint to receive 'PHA Form A' sample data.
- Perform basic field validation of form data from POST request data.

- Extract key user extract demographic data from POST request data.
- Transform key user demographic data into a new JSON Document Schema called PhaPersonInfo.

### Data Intake API: Phase 2 Objectives

Phase 2 of the PHA Form A Data Intake API shall build upon the Phase 1 Objectives and expose the following capabilities.

- [pending testing] Modify POST request to return only an HTTP 200 status code on successful message validation, parsing and transformation.
- [next action] Integrate the Data Intake environment with an ActiveMQ messaging system.

- Configure and instantiate a JMS Producer Subsystem to publish JSON Documents to target queue (PHA_FORM_A).
- Configure and instantiate a JMS Consumer Subsystem to retrieve and persist JSON Documents from target queue (PHA_FORM_A).

- Publish the JMS Producer and JMS Consumer Subsystems to WAR files to be loaded into Enterprise Java Application server.

## Project Constraints

- The application shall use only standard server development libraries.
    - 	e.g. Spring Boot, Jackson.

## Key User Demographic Data To Validate

Key fields from the POST Request data will be validated for existence and adherence to basic regular expression based validation rules.

Each of the following fields are checked for existence and provide aggregated validation error messages.

    - Input JSON Payload 
    - User Email Address
  
    - User First Name
    - User Last Name
  
    - User Data of Birth
    - User DODID

## Key User Demographic Data To Extract

The API will extract and load the following user demographic data.

- User Name
- User Mailing Address

- User PHone Number
- User Email Address

- User Date of Birth
- User DOD Id

- User Gender
- User Service

- User Rank
