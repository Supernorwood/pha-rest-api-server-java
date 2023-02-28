package com.pha.health.validation;

/**
 * The Result of the validation attempt of a PHA Form A Person JSON Document
 * <p>
 * This Object contains a boolean validation status and a Data validation notification string.
 */
public class ValidationStatusAndMessage {

    /**
     * Did the content pass the data validation checks?
     */
    Boolean wasDataValidationSuccess;

    /**
     * Data Validation Notifications
     */
    String dataValidationMessage;

    public ValidationStatusAndMessage() {

    }

    public ValidationStatusAndMessage(Boolean wasDataValidationSuccess, String dataValidationMessage) {

        this.wasDataValidationSuccess = wasDataValidationSuccess;
        this.dataValidationMessage = dataValidationMessage;

    }

    public Boolean getWasDataValidationSuccess() {
        return this.wasDataValidationSuccess;
    }

    public void setDataValidationSuccessStatus(Boolean valid) {
        this.wasDataValidationSuccess = valid;
    }

    public String getDataValidationMessage() {
        return this.dataValidationMessage;
    }

    public void setDataValidationMessage(String dataValidationMessage) {
        this.dataValidationMessage = dataValidationMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ValidationStatusAndMessage that = (ValidationStatusAndMessage) o;

        if (!wasDataValidationSuccess.equals(that.wasDataValidationSuccess)) return false;
        return dataValidationMessage.equals(that.dataValidationMessage);
    }

    @Override
    public int hashCode() {
        int result = wasDataValidationSuccess.hashCode();
        result = 31 * result + dataValidationMessage.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ValidationStatusAndMessage{" +
                "isValid=" + wasDataValidationSuccess +
                ", validationNotification='" + dataValidationMessage + '\'' +
                '}';
    }
}
