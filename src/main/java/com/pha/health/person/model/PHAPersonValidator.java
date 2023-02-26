package com.pha.health.person.model;

import com.pha.health.validation.ValidationStatusAndMessage;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validates the JSON Object Representation of a PHA Person
 */
public class PHAPersonValidator {

    /**
     * Checks and validates a JSON String .
     * <p>
     * The validation checks that the JSON String contains key important data and key expected.
     *
     * @param jsonString JSON String representation of a PHA Person
     * @return True, if the JSON String is valid and contains the key important data.
     */
    public static ValidationStatusAndMessage validateUserJSONString(String jsonString) {

        ValidationStatusAndMessage validationStatusAndMessage = new ValidationStatusAndMessage();

        if (jsonString == null) {

            validationStatusAndMessage.setValid(false);
            validationStatusAndMessage.setValidationNotification("Input JSON String was null");

        } else if (jsonString.isEmpty()) {

            validationStatusAndMessage.setValid(false);
            validationStatusAndMessage.setValidationNotification("Input JSON String was empty");

        } else if (jsonString.length() < 1) {

            validationStatusAndMessage.setValid(false);
            validationStatusAndMessage.setValidationNotification("Input JSON String was too short");

        } else if (jsonString == "") {

            validationStatusAndMessage.setValid(false);
            validationStatusAndMessage.setValidationNotification("Input JSON String was empty");

        } else {

            validationStatusAndMessage.setValid(true);
            validationStatusAndMessage.setValidationNotification("Input JSON String passed validation checks");

        }

        return validationStatusAndMessage;
    }

    /**
     * Checks and validates a Person's Email Address.
     * <p>
     * The validation checks that the Email Address matches this regular expression:  "^(.+)@(.+)$"
     *
     * @param jsonObject JSONObject representation of a Person
     * @return True, if the user email matches the regular expression. False, if not.
     */
    public static ValidationStatusAndMessage validateUserJSONObject(JSONObject jsonObject) {


        ValidationStatusAndMessage isUserFirstNameValid = validateUserFirstName(jsonObject);
        ValidationStatusAndMessage isUserLastNameValid = validateUserLastName(jsonObject);

        ValidationStatusAndMessage isUserEmailAddressValid = validateUserEmailAddress(jsonObject);
        ValidationStatusAndMessage isUserPhoneNumberValid = validateUserPhoneNumber(jsonObject);

        ValidationStatusAndMessage isUserDODIdentifierValid = validatePersonDODIdentifier(jsonObject);

        //need to aggregate here

        ValidationStatusAndMessage aggregatedValidationMessages = new ValidationStatusAndMessage();

        aggregatedValidationMessages.setValid(
                isUserFirstNameValid.getValidationStatus() &&
                        isUserLastNameValid.getValidationStatus() &&
                        isUserEmailAddressValid.getValidationStatus() &&
                        isUserPhoneNumberValid.getValidationStatus() &&
                        isUserDODIdentifierValid.getValidationStatus());

        StringBuffer stringBuffer = new StringBuffer();

        if (!isUserFirstNameValid.getValidationStatus())
            stringBuffer.append(isUserFirstNameValid.getValidationNotification());

        if (!isUserLastNameValid.getValidationStatus())
            stringBuffer.append(isUserLastNameValid.getValidationNotification());

        if (!isUserEmailAddressValid.getValidationStatus())
            stringBuffer.append(isUserEmailAddressValid.getValidationNotification());

        if (!isUserPhoneNumberValid.getValidationStatus())
            stringBuffer.append(isUserPhoneNumberValid.getValidationNotification());

        if (!isUserDODIdentifierValid.getValidationStatus())
            stringBuffer.append(isUserDODIdentifierValid.getValidationNotification());

        aggregatedValidationMessages.setValidationNotification(stringBuffer.toString());

        return aggregatedValidationMessages;
    }

    /**
     * Checks and validates a Person's First Name.
     * <p>
     * The validation checks that the First Name is not null and is not empty.
     *
     * @param jsonObject JSONObject representation of a Person
     * @return True, if the first name is non-null and non-empty. False, if not.
     */
    private static ValidationStatusAndMessage validateUserFirstName(JSONObject jsonObject) {

        ValidationStatusAndMessage validationStatusAndMessage = new ValidationStatusAndMessage();

        String personFirstName = jsonObject.getJSONObject(PHAPersonKeys.PHA_Q_RECORD).getString(PHAPersonKeys.FIRST_NAME);

        if (personFirstName == null) {

            validationStatusAndMessage.setValid(false);
            validationStatusAndMessage.setValidationNotification("Person First Name was null");

        } else if (personFirstName.isEmpty()) {

            validationStatusAndMessage.setValid(false);
            validationStatusAndMessage.setValidationNotification("Person First Name was empty");

        } else if (personFirstName.length() < 1) {

            validationStatusAndMessage.setValid(false);
            validationStatusAndMessage.setValidationNotification("Person First Name was too short");

        } else if (personFirstName == "") {

            validationStatusAndMessage.setValid(false);
            validationStatusAndMessage.setValidationNotification("Person First Name was empty");

        } else {

            validationStatusAndMessage.setValid(true);
            validationStatusAndMessage.setValidationNotification("Person First Name passed validation checks");

        }

        return validationStatusAndMessage;
    }

    /**
     * Checks and validates a Person's Last Name.
     * <p>
     * The validation checks that the Last Name is not null and is not empty.
     *
     * @param jsonObject JSONObject representation of a Person
     * @return True, if the last name is non-null and non-empty. False, if not.
     */
    private static ValidationStatusAndMessage validateUserLastName(JSONObject jsonObject) {

        ValidationStatusAndMessage validationStatusAndMessage = new ValidationStatusAndMessage();

        String personLastName = jsonObject.getJSONObject(PHAPersonKeys.PHA_Q_RECORD).getString(PHAPersonKeys.LAST_NAME);

        if (personLastName == null) {

            validationStatusAndMessage.setValid(false);
            validationStatusAndMessage.setValidationNotification("Person Last Name was null. ");

        } else if (personLastName.isEmpty()) {

            validationStatusAndMessage.setValid(false);
            validationStatusAndMessage.setValidationNotification("Person Last Name was empty.");

        } else if (personLastName.length() < 1) {

            validationStatusAndMessage.setValid(false);
            validationStatusAndMessage.setValidationNotification("Person Last Name was too short.");

        } else if (personLastName == "") {

            validationStatusAndMessage.setValid(false);
            validationStatusAndMessage.setValidationNotification("Person Last Name was empty.");

        } else {

            validationStatusAndMessage.setValid(true);
            validationStatusAndMessage.setValidationNotification("Person Last Name passed validation checks.");

        }

        return validationStatusAndMessage;
    }

    /**
     * Checks and validates a Person's Phone Number.
     * <p>
     * The validation checks that the Phone Number matches this regular expression: "^[\\d]{10}$"
     *
     * @param jsonObject JSONObject representation of a Person
     * @return True, if the user phone number matches the regular expression. False, if not.
     */
    public static ValidationStatusAndMessage validateUserPhoneNumber(JSONObject jsonObject) {

        ValidationStatusAndMessage validationStatusAndMessage = new ValidationStatusAndMessage();

        String personPhoneNumber = jsonObject.getJSONObject(PHAPersonKeys.PHA_Q_RECORD).getString(PHAPersonKeys.PRIMARY_PHONE_NUMBER);

        String validPhoneNumberRegularExpression = "^[\\d]{10}$";

        Pattern pattern = Pattern.compile(validPhoneNumberRegularExpression);
        Matcher matcher = pattern.matcher(personPhoneNumber);

        boolean doesPhoneNumberFitCommonFormat = matcher.matches();

        if (doesPhoneNumberFitCommonFormat) {

            validationStatusAndMessage.setValid(true);
            validationStatusAndMessage.setValidationNotification("Person Phone Number Format Was Valid. ");

        } else {

            validationStatusAndMessage.setValid(false);
            validationStatusAndMessage.setValidationNotification("Person Phone Number Formatted Incorrectly. ");

        }

        return validationStatusAndMessage;
    }

    /**
     * Checks and validates a Person's DOD Identifier.
     * <p>
     * The validation checks that the DOD Identifier is not null and is not empty.
     *
     * @param jsonObject JSONObject representation of a Person
     * @return True, if the DOD Identifier is non-null and non-empty. False, if not.
     */
    private static ValidationStatusAndMessage validatePersonDODIdentifier(JSONObject jsonObject) {

        ValidationStatusAndMessage validationStatusAndMessage = new ValidationStatusAndMessage();

        String personDODIdentifier = jsonObject.getJSONObject(PHAPersonKeys.PHA_Q_RECORD).getString(PHAPersonKeys.DOD_ID);

        if (personDODIdentifier == null) {

            validationStatusAndMessage.setValid(false);
            validationStatusAndMessage.setValidationNotification("DOD Identifier Was null. ");

        } else if (personDODIdentifier.isEmpty()) {

            validationStatusAndMessage.setValid(false);
            validationStatusAndMessage.setValidationNotification("DOD Identifier Was empty. ");

        } else if (personDODIdentifier.length() < 1) {

            validationStatusAndMessage.setValid(false);
            validationStatusAndMessage.setValidationNotification("DOD Identifier Was too short. ");

        } else if (personDODIdentifier == "") {

            validationStatusAndMessage.setValid(false);
            validationStatusAndMessage.setValidationNotification("DOD Identifier Was empty. ");

        } else {

            validationStatusAndMessage.setValid(true);
            validationStatusAndMessage.setValidationNotification("DOD Identifier passed validation checks. ");

        }

        return validationStatusAndMessage;
    }

    private static ValidationStatusAndMessage validateUserEmailAddress(JSONObject jsonObject) {

        ValidationStatusAndMessage validationStatusAndMessage = new ValidationStatusAndMessage();

        String personEmailAddress = jsonObject.getJSONObject(PHAPersonKeys.PHA_Q_RECORD).getString(PHAPersonKeys.EMAIL_ADDRESS);

        String validEmailRegularExpression = "^(.+)@(.+)$";

        Pattern pattern = Pattern.compile(validEmailRegularExpression);

        Matcher matcher = pattern.matcher(personEmailAddress);

        boolean isUserEmailValid = matcher.matches();

        if (isUserEmailValid) {

            validationStatusAndMessage.setValid(true);
            validationStatusAndMessage.setValidationNotification("Person Email Address Format Was Valid. ");

        } else {

            validationStatusAndMessage.setValid(false);
            validationStatusAndMessage.setValidationNotification("Person Email Address Formatted Incorrectly. ");

        }

        return validationStatusAndMessage;
    }

}
