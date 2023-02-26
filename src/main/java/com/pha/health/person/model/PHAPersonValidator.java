package com.pha.health.person.model;

import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validates the JSON Object Representation of a PHA Person
 */
public class PHAPersonValidator {

    /**
     * Checks and validates a Person's Email Address.
     * <p>
     * The validation checks that the Email Address matches this regular expression:  "^(.+)@(.+)$"
     *
     * @param jsonObject JSONObject representation of a Person
     * @return True, if the user email matches the regular expression. False, if not.
     */
       public static Boolean validateUserJSONObject(JSONObject jsonObject) {

        boolean isUserFirstNameValid = validateUserFirstName(jsonObject);
        boolean isUserLastNameValid = validateUserLastName(jsonObject);

        boolean isUserEmailAddressValid = validateUserEmailAddress(jsonObject);
        boolean isUserPhoneNumberValid = validateUserPhoneNumber(jsonObject);

        boolean isUserDODIdentifierValid = validatePersonDODIdentifier(jsonObject);

        return (isUserFirstNameValid && isUserLastNameValid && isUserEmailAddressValid && isUserPhoneNumberValid && isUserDODIdentifierValid);
    }

    /**
     * Checks and validates a Person's First Name.
     * <p>
     * The validation checks that the First Name is not null and is not empty.
     *
     * @param jsonObject JSONObject representation of a Person
     * @return True, if the first name is non-null and non-empty. False, if not.
     */
    private static boolean validateUserFirstName(JSONObject jsonObject) {

        String personFirstName = jsonObject.getJSONObject(PHAPersonKeys.PHA_Q_RECORD).getString(PHAPersonKeys.FIRST_NAME);

        boolean isValidFirstName = true;

        if (personFirstName == null) {

            isValidFirstName = false;

        } else if (personFirstName.isEmpty()) {

            isValidFirstName = false;

        } else if (personFirstName.length() < 1) {

            isValidFirstName = false;

        } else if (personFirstName == "") {

            isValidFirstName = false;

        }

        return isValidFirstName;
    }

    /**
     * Checks and validates a Person's Last Name.
     * <p>
     * The validation checks that the Last Name is not null and is not empty.
     *
     * @param jsonObject JSONObject representation of a Person
     * @return True, if the last name is non-null and non-empty. False, if not.
     */
    private static boolean validateUserLastName(JSONObject jsonObject) {

        String personLastName = jsonObject.getJSONObject(PHAPersonKeys.PHA_Q_RECORD).getString(PHAPersonKeys.LAST_NAME);

        boolean isValidLastName = true;

        if (personLastName == null) {

            isValidLastName = false;

        } else if (personLastName.isEmpty()) {

            isValidLastName = false;

        } else if (personLastName.length() < 1) {

            isValidLastName = false;

        } else if (personLastName == "") {

            isValidLastName = false;

        }

        return isValidLastName;
    }

    /**
     * Checks and validates a Person's Phone Number.
     * <p>
     * The validation checks that the Phone Number matches this regular expression: "^[\\d]{10}$"
     *
     * @param jsonObject JSONObject representation of a Person
     * @return True, if the user phone number matches the regular expression. False, if not.
     */
    public static Boolean validateUserPhoneNumber(JSONObject jsonObject) {

        String personPhoneNumber = jsonObject.getJSONObject(PHAPersonKeys.PHA_Q_RECORD).getString(PHAPersonKeys.PRIMARY_PHONE_NUMBER);

        String validPhoneNumberRegularExpression = "^[\\d]{10}$";

        Pattern pattern = Pattern.compile(validPhoneNumberRegularExpression);

        Matcher matcher = pattern.matcher(personPhoneNumber);

        boolean isValidPhoneNumber = matcher.matches();

        return isValidPhoneNumber;
    }

    /**
     * Checks and validates a Person's DOD Identifier.
     * <p>
     * The validation checks that the DOD Identifier is not null and is not empty.
     *
     * @param jsonObject JSONObject representation of a Person
     * @return True, if the DOD Identifier is non-null and non-empty. False, if not.
     */
    private static boolean validatePersonDODIdentifier(JSONObject jsonObject) {

        String personDODIdentifier = jsonObject.getJSONObject(PHAPersonKeys.PHA_Q_RECORD).getString(PHAPersonKeys.DOD_ID);

        boolean isValidDODIdentifier = true;

        if (personDODIdentifier == null) {

            isValidDODIdentifier = false;

        } else if (personDODIdentifier.isEmpty()) {

            isValidDODIdentifier = false;

        } else if (personDODIdentifier.length() < 1) {

            isValidDODIdentifier = false;

        } else if (personDODIdentifier == "") {

            isValidDODIdentifier = false;

        }

        return isValidDODIdentifier;
    }

    private static boolean validateUserEmailAddress(JSONObject jsonObject) {

        String personEmailAddress = jsonObject.getJSONObject(PHAPersonKeys.PHA_Q_RECORD).getString(PHAPersonKeys.EMAIL_ADDRESS);

        String validEmailRegularExpression = "^(.+)@(.+)$";

        Pattern pattern = Pattern.compile(validEmailRegularExpression);

        Matcher matcher = pattern.matcher(personEmailAddress);

        boolean isUserEmailValid = matcher.matches();

        return isUserEmailValid;
    }

}
