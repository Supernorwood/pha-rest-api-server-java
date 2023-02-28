package com.pha.health.person.model;

import org.json.JSONObject;

/**
 * Provides Helper methods to read and write a PHA Form A Person to and from JSON Documents and PHAPerson Objects.
 */
public class PHAPersonHelper {

    /**
     * Create and populate a PHAPerson Object with content from the input JSONObject.
     */
    public static PHAPerson readPHAPersonFromJSONObject(JSONObject jsonObject) {

        // Create a new PHA Person Object
        PHAPerson loadedPHAPerson = new PHAPerson();

        // Read and set key PHA Person Data
        loadedPHAPerson.setFirstName(jsonObject.getJSONObject(PHAPersonKeys.PHA_Q_RECORD).getString(PHAPersonKeys.FIRST_NAME));
        loadedPHAPerson.setLastName(jsonObject.getJSONObject(PHAPersonKeys.PHA_Q_RECORD).getString(PHAPersonKeys.LAST_NAME));

        loadedPHAPerson.setDateOfBirth(jsonObject.getJSONObject(PHAPersonKeys.PHA_Q_RECORD).getString(PHAPersonKeys.DATE_OF_BIRTH));
        loadedPHAPerson.setGender(jsonObject.getJSONObject(PHAPersonKeys.PHA_Q_RECORD).getString(PHAPersonKeys.GENDER));

        loadedPHAPerson.setMailingAddress(jsonObject.getJSONObject(PHAPersonKeys.PHA_Q_RECORD).getString(PHAPersonKeys.MAILING_ADDRESS));
        loadedPHAPerson.setPhoneNumber(jsonObject.getJSONObject(PHAPersonKeys.PHA_Q_RECORD).getString(PHAPersonKeys.PRIMARY_PHONE_NUMBER));
        loadedPHAPerson.setEmailAddress(jsonObject.getJSONObject(PHAPersonKeys.PHA_Q_RECORD).getString(PHAPersonKeys.EMAIL_ADDRESS));

        loadedPHAPerson.setDodID(jsonObject.getJSONObject(PHAPersonKeys.PHA_Q_RECORD).getString(PHAPersonKeys.DOD_ID));

        loadedPHAPerson.setService(jsonObject.getJSONObject(PHAPersonKeys.PHA_Q_RECORD).getString(PHAPersonKeys.SERVICE));
        loadedPHAPerson.setRank(jsonObject.getJSONObject(PHAPersonKeys.PHA_Q_RECORD).getString(PHAPersonKeys.RANK));

        // Return the created and populated PHA Person
        return loadedPHAPerson;
    }

    /**
     * Reads the values of a input PHAPerson object and converts content into an organized output JSON Object.
     */
    public static JSONObject writeJSONObjectFromPHAPersonObject(PHAPerson phaPerson) {

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
