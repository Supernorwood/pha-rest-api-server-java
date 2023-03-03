package com.pha.health.person.model;

import org.json.JSONObject;

/**
 * Provides Helper methods to read and write a PHA Form A Person to and from JSON Documents and PHAPerson Objects.
 */
public class PHAPersonHelper {

    /**
     * Create and populate a PHAPerson Object with content from the input JSONObject.
     */
    public static PHAPerson unmarshallDataFromJSONToPHAPerson(JSONObject jsonObject) {

        // Create a new PHA Person Object
        PHAPerson loadedPHAPerson = new PHAPerson();

        // Read and set key PHA Person Data
        loadedPHAPerson.setFirstName(jsonObject.getJSONObject("phaqRecord").getString("fname"));
        loadedPHAPerson.setLastName(jsonObject.getJSONObject("phaqRecord").getString("lname"));

        loadedPHAPerson.setDateOfBirth(jsonObject.getJSONObject("phaqRecord").getString("dob"));
        loadedPHAPerson.setGender(jsonObject.getJSONObject("phaqRecord").getString("sex"));

        loadedPHAPerson.setMailingAddress(jsonObject.getJSONObject("phaqRecord").getString("addr"));
        loadedPHAPerson.setPhoneNumber(jsonObject.getJSONObject("phaqRecord").getString("phoneDay"));
        loadedPHAPerson.setEmailAddress(jsonObject.getJSONObject("phaqRecord").getString ("email1"));

        loadedPHAPerson.setDodID(jsonObject.getJSONObject("phaqRecord").getString("dodId"));

        loadedPHAPerson.setService(jsonObject.getJSONObject("phaqRecord").getString( "serviceOther"));
        loadedPHAPerson.setRank(jsonObject.getJSONObject("phaqRecord").getString( "grade"));

        // Return the created and populated PHA Person
        return loadedPHAPerson;
    }

    /**
     * Map the values of a input PHAPerson object and converts content into an organized output JSON Object.
     * 
     * The transformation from one JSON Document type to another happens here.
     */
    public static JSONObject transformPHAPersonToNestedJSON(PHAPerson phaPerson) {

        // Create a new JSON Objct
        JSONObject phaPersonJSONObject = new JSONObject();

        //

        // Create the Personal Data JSON Child Node Object
        JSONObject phaPersonPersonalData = new JSONObject();

        // Populate Content of Personal Data child node
        phaPersonPersonalData.put("firstName", phaPerson.getFirstName());
        phaPersonPersonalData.put("lastName", phaPerson.getLastName());
        phaPersonPersonalData.put("dateOfBirth", phaPerson.getDateOfBirth());
        phaPersonPersonalData.put("gender", phaPerson.getGender());

        // Attach the Personal Data JSON Child  to the PHA Person JSON Object
        phaPersonJSONObject.put("personalData", phaPersonPersonalData);

        //

        // Create the Address Data JSON Child Node Object
        JSONObject phaPersonAddressData = new JSONObject();

        // Populate Content of Address Data child node
        phaPersonAddressData.put("mailingAddress", phaPerson.getMailingAddress());

        // Attach the Address Data JSON Child to the PHA Person JSON Object
        phaPersonJSONObject.put("addressData", phaPersonAddressData);

        //

        // Create the Contact Data JSON Child Node Object
        JSONObject phaPersonContactData = new JSONObject();

        // Populate Content of Contact Data child node
        phaPersonContactData.put("phoneNumber", phaPerson.getPhoneNumber());
        phaPersonContactData.put("emailAddress", phaPerson.getEmailAddress());

        // Attach the Contact Data JSON Child to the PHA Person JSON Object
        phaPersonJSONObject.put("contactData", phaPersonContactData);

        //

        // Create the Service Data JSON Child Node Object
        JSONObject phaPersonServiceData = new JSONObject();

        // Populate Content of Service Data child node
        phaPersonServiceData.put("service", phaPerson.getService());
        phaPersonServiceData.put("rank", phaPerson.getRank());
        phaPersonServiceData.put("dodID", phaPerson.getDodID());

        // Attach the Service Data JSON Child to the PHA Person JSON Object
        phaPersonJSONObject.put("serviceData", phaPersonServiceData);

        //

        // Return the fully build PHA Person JSON Object
        return phaPersonJSONObject;
    }

}
