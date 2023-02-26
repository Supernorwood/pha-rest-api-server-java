package com.pha.health.person.model;

import org.json.JSONObject;

/**
 * Provides methods to write a PHA Person to and from JSON Objects.
 */
public class PHAPersonHelper {

    /**
     * Reads a PHA Person Object from a JSON Object.
     */
    public static PHAPerson loadFrom(JSONObject jsonObject) {

        PHAPerson loadedPHAPerson = new PHAPerson();

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

        return loadedPHAPerson;
    }

    /**
     * Writes a PHA Person Object to a JSON Object.
     */
    public static JSONObject toJSON(PHAPerson phaPerson) {

        JSONObject phaPersonJSONObject = new JSONObject();

        //

        JSONObject phaPersonPersonalData = new JSONObject();

        phaPersonPersonalData.put("firstName", phaPerson.getFirstName());
        phaPersonPersonalData.put("lastName", phaPerson.getLastName());
        phaPersonPersonalData.put("dateOfBirth", phaPerson.getDateOfBirth());
        phaPersonPersonalData.put("gender", phaPerson.getGender());

        phaPersonJSONObject.put("personalData", phaPersonPersonalData);

        //

        JSONObject phaPersonAddressData = new JSONObject();

        phaPersonAddressData.put("mailingAddress", phaPerson.getMailingAddress());

        phaPersonJSONObject.put("addressData", phaPersonAddressData);

        //

        JSONObject phaPersonContactData = new JSONObject();

        phaPersonContactData.put("phoneNumber", phaPerson.getPhoneNumber());
        phaPersonContactData.put("emailAddress", phaPerson.getEmailAddress());

        phaPersonJSONObject.put("contactData", phaPersonContactData);

        //

        JSONObject phaPersonServiceData = new JSONObject();

        phaPersonServiceData.put("service", phaPerson.getService());
        phaPersonServiceData.put("rank", phaPerson.getRank());
        phaPersonServiceData.put("dodID", phaPerson.getDodID());

        phaPersonJSONObject.put("serviceData", phaPersonServiceData);

        return phaPersonJSONObject;
    }

}
