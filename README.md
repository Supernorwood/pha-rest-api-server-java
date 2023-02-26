# pha-rest-api-server-java# PHA Form A Challenge

## Part 1

### to do
	- [wip] Add some basic validation. 
Come up w/ meaningful validation messages for a few required fields:
- input message itself, required First and Last Names, required DOB, DODID, Email

### to done

	- [done] Create an endpoint (POST) to receive sample PHA Form A
	- [done] Unmarshall and parse the JSON doc

	 -[done] Extract demographic data (Name, Address, Phone, Email, DOB, DODID, Gender, Service, Rank)
	- [done]Design a new JSON doc called PhaPersonInfo.  Have seperate subsections for personal data, address data,
	  contact data, service data.

	- [done] Return new JSON doc as response (HTTP 200).
    - [done]comment the source cod 
- put stuff into git
- - [done] Use standard libs (Spring Boot, Jackson)

## Part 2

	- Modify Part 1. Instead of returning new JSON doc in a response, just return HTTP 200 if message validates and is
	  successfully parsed and transformed.

	- Download and install Active MQ.
	- Add a message producer to send new JSON doc to a queue in AMQ.  Call the queue PHA_FORM_A.

	- Write a message consumer (seperate project and WAR) to retrieve the message from the queue, and
	  write it out to a local file.