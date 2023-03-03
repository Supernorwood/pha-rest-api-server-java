package com.pha.health.person.validation;

import com.pha.health.person.model.PHAPersonKeys;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validates the JSON Object Representation of a PHA Form A Person.
 * <p>
 * In short, {@code PHAPersonValidator} does the heavy lifting for content validation of PHA Form A JSON data.
 */
public class PHAPersonValidator {

    /**
     * Checks and validates that an input JSON String contains the expected values.
     * <p>
     * The validation checks that the JSON String contains key important data and key expected.
     *
     * @param jsonString JSON String fetched from the API POST request call.
     * @return Aggregated ValidationStatusAndMessage of the results of input String validation.
     */
    public static ValidationStatusAndMessage validateUserJSONString(String jsonString) {

        // Creates a new validation results object
        ValidationStatusAndMessage validationStatusAndMessage = new ValidationStatusAndMessage();

        if (jsonString == null) { // check that the input json string is not null

            validationStatusAndMessage.setDataValidationSuccessStatus(false);
            validationStatusAndMessage.setDataValidationMessage("Input JSON String was null");


        } else if (jsonString.isEmpty()) { // check that the input json string is not empty

            validationStatusAndMessage.setDataValidationSuccessStatus(false);
            validationStatusAndMessage.setDataValidationMessage("Input JSON String was empty");

        } else if (jsonString.length() < 1) { // check that the input json string is not too short

            validationStatusAndMessage.setDataValidationSuccessStatus(false);
            validationStatusAndMessage.setDataValidationMessage("Input JSON String was too short");

        } else if (jsonString == "") {  // check that the input json string is not a nuyll string

            validationStatusAndMessage.setDataValidationSuccessStatus(false);
            validationStatusAndMessage.setDataValidationMessage("Input JSON String was empty");

        } else { // handle default validation scenario

            validationStatusAndMessage.setDataValidationSuccessStatus(true);
            validationStatusAndMessage.setDataValidationMessage("Input JSON String passed validation checks");

        }

        // return the status of the data validation
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
    public static ValidationStatusAndMessage validateEntireUserJSONObject(JSONObject jsonObject) {

        // Create ValidationStatusAndMessage for the user first name validation
        ValidationStatusAndMessage isUserFirstNameValid = validateUserFirstName(jsonObject);

        // Create ValidationStatusAndMessage for the user last name validation
        ValidationStatusAndMessage isUserLastNameValid = validateUserLastName(jsonObject);

        // Create ValidationStatusAndMessage for the user email validation
        ValidationStatusAndMessage isUserEmailAddressValid = validateUserEmailAddress(jsonObject);

        // Create ValidationStatusAndMessage for the user phone number validation
        ValidationStatusAndMessage isUserPhoneNumberValid = validateUserPhoneNumber(jsonObject);

        // Create ValidationStatusAndMessage for the user DOD Id validation
        ValidationStatusAndMessage isUserDODIdentifierValid = validatePersonDODIdentifier(jsonObject);

        // Create ValidationStatusAndMessage to hold the aggregation of all data validations
        ValidationStatusAndMessage aggregatedValidationMessages = new ValidationStatusAndMessage();

        // calculate boolean logic of total user validation from each of the smaller validations and set the boolean status value
        aggregatedValidationMessages.setDataValidationSuccessStatus(
                isUserFirstNameValid.getWasDataValidationSuccess() &&
                        isUserLastNameValid.getWasDataValidationSuccess() &&
                        isUserEmailAddressValid.getWasDataValidationSuccess() &&
                        isUserPhoneNumberValid.getWasDataValidationSuccess() &&
                        isUserDODIdentifierValid.getWasDataValidationSuccess());

        // Create string buffer to aggregate each of the data validation message Strings
        StringBuffer stringBuffer = new StringBuffer();

        // if user first name validation error, add the validation message to the string buffer
        if (!isUserFirstNameValid.getWasDataValidationSuccess())
            stringBuffer.append(isUserFirstNameValid.getDataValidationMessage());

        // if user last name validation error, add the validation message to the string buffer
        if (!isUserLastNameValid.getWasDataValidationSuccess())
            stringBuffer.append(isUserLastNameValid.getDataValidationMessage());

        // if user email validation error, add the validation message to the string buffer
        if (!isUserEmailAddressValid.getWasDataValidationSuccess())
            stringBuffer.append(isUserEmailAddressValid.getDataValidationMessage());

        // if user phone number validation error, add the validation message to the string buffer
        if (!isUserPhoneNumberValid.getWasDataValidationSuccess())
            stringBuffer.append(isUserPhoneNumberValid.getDataValidationMessage());

        // if user dod id validation error, add the validation message to the string buffer
        if (!isUserDODIdentifierValid.getWasDataValidationSuccess())
            stringBuffer.append(isUserDODIdentifierValid.getDataValidationMessage());

        // set the aggregated validation messages to the aggregated ValidationStatusAndMessage
        aggregatedValidationMessages.setDataValidationMessage(stringBuffer.toString());

        //  return the aggregated validation aggregatedValidationMessages
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

            validationStatusAndMessage.setDataValidationSuccessStatus(false);
            validationStatusAndMessage.setDataValidationMessage("Person First Name was null");

        } else if (personFirstName.isEmpty()) {

            validationStatusAndMessage.setDataValidationSuccessStatus(false);
            validationStatusAndMessage.setDataValidationMessage("Person First Name was empty");

        } else if (personFirstName.length() < 1) {

            validationStatusAndMessage.setDataValidationSuccessStatus(false);
            validationStatusAndMessage.setDataValidationMessage("Person First Name was too short");

        } else if (personFirstName == "") {

            validationStatusAndMessage.setDataValidationSuccessStatus(false);
            validationStatusAndMessage.setDataValidationMessage("Person First Name was empty");

        } else {

            validationStatusAndMessage.setDataValidationSuccessStatus(true);
            validationStatusAndMessage.setDataValidationMessage("Person First Name passed validation checks");

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

            validationStatusAndMessage.setDataValidationSuccessStatus(false);
            validationStatusAndMessage.setDataValidationMessage("Person Last Name was null. ");

        } else if (personLastName.isEmpty()) {

            validationStatusAndMessage.setDataValidationSuccessStatus(false);
            validationStatusAndMessage.setDataValidationMessage("Person Last Name was empty.");

        } else if (personLastName.length() < 1) {

            validationStatusAndMessage.setDataValidationSuccessStatus(false);
            validationStatusAndMessage.setDataValidationMessage("Person Last Name was too short.");

        } else if (personLastName == "") {

            validationStatusAndMessage.setDataValidationSuccessStatus(false);
            validationStatusAndMessage.setDataValidationMessage("Person Last Name was empty.");

        } else {

            validationStatusAndMessage.setDataValidationSuccessStatus(true);
            validationStatusAndMessage.setDataValidationMessage("Person Last Name passed validation checks.");

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

        // regular expression variables
        Pattern pattern = Pattern.compile(validPhoneNumberRegularExpression);
        Matcher matcher = pattern.matcher(personPhoneNumber);

        boolean doesPhoneNumberFitCommonFormat = matcher.matches();

        if (doesPhoneNumberFitCommonFormat) {

            validationStatusAndMessage.setDataValidationSuccessStatus(true);
            validationStatusAndMessage.setDataValidationMessage("Person Phone Number Format Was Valid. ");

        } else {

            validationStatusAndMessage.setDataValidationSuccessStatus(false);
            validationStatusAndMessage.setDataValidationMessage("Person Phone Number Formatted Incorrectly. ");

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

            validationStatusAndMessage.setDataValidationSuccessStatus(false);
            validationStatusAndMessage.setDataValidationMessage("DOD Identifier Was null. ");

        } else if (personDODIdentifier.isEmpty()) {

            validationStatusAndMessage.setDataValidationSuccessStatus(false);
            validationStatusAndMessage.setDataValidationMessage("DOD Identifier Was empty. ");

        } else if (personDODIdentifier.length() < 1) {

            validationStatusAndMessage.setDataValidationSuccessStatus(false);
            validationStatusAndMessage.setDataValidationMessage("DOD Identifier Was too short. ");

        } else if (personDODIdentifier == "") {

            validationStatusAndMessage.setDataValidationSuccessStatus(false);
            validationStatusAndMessage.setDataValidationMessage("DOD Identifier Was empty. ");

        } else {

            validationStatusAndMessage.setDataValidationSuccessStatus(true);
            validationStatusAndMessage.setDataValidationMessage("DOD Identifier passed validation checks. ");

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

            validationStatusAndMessage.setDataValidationSuccessStatus(true);
            validationStatusAndMessage.setDataValidationMessage("Person Email Address Format Was Valid. ");

        } else {

            validationStatusAndMessage.setDataValidationSuccessStatus(false);
            validationStatusAndMessage.setDataValidationMessage("Person Email Address Formatted Incorrectly. ");

        }

        return validationStatusAndMessage;
    }

}
