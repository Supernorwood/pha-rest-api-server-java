package com.pha.health.validation;

public class ValidationStatusAndMessage {

    Boolean validationStatus;
    String validationNotification;

    public ValidationStatusAndMessage() {

    }

    public ValidationStatusAndMessage(Boolean validationStatus, String validationNotification) {

        this.validationStatus = validationStatus;
        this.validationNotification = validationNotification;

    }

    public Boolean getValidationStatus() {
        return validationStatus;
    }

    public void setValid(Boolean valid) {
        validationStatus = valid;
    }

    public String getValidationNotification() {
        return validationNotification;
    }

    public void setValidationNotification(String validationNotification) {
        this.validationNotification = validationNotification;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ValidationStatusAndMessage that = (ValidationStatusAndMessage) o;

        if (!validationStatus.equals(that.validationStatus)) return false;
        return validationNotification.equals(that.validationNotification);
    }

    @Override
    public int hashCode() {
        int result = validationStatus.hashCode();
        result = 31 * result + validationNotification.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ValidationStatusAndMessage{" +
                "isValid=" + validationStatus +
                ", validationNotification='" + validationNotification + '\'' +
                '}';
    }
}
