package com.pha.health.person.api;

import com.pha.health.jms.producer.JMSQueueProducer;
import com.pha.health.person.model.PHAPersonHelper;
import com.pha.health.person.validation.PHAPersonValidator;
import com.pha.health.person.validation.PHAPersonValidationStatusAndMessage;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import javax.jms.JMSException;

/**
 * Restful PHA Person Validation API Controller for the PHA Person API
 */
@CrossOrigin
@RestController
@RequestMapping("/pha/person")
public class PHAPersonRestController {

    private static final String host = "localhost";
    private static final int port = 61616;
    private static final String queueName = "PHA_FORM_A";

    /**
     * Default Constructor for the PHA Person Validator RESTController
     */
    public PHAPersonRestController() {
        System.out.println("init -- PHA Person API");
    }

    /**
     * Return a health check message.
     */
    @GetMapping("/health-check")
    public String returnHealthCheckContentJSON() {
        return "PHA Form A Data Intake API Is Up!";
    }

    /**
     * Validate and transform the JSON String representation of a PHA Person.
     */
    @PostMapping("/transform-user-data")
    public ResponseEntity<String> transformUserData(@RequestBody String phaUserJSONString) throws JMSException {

        // Take the JSON String and Create a populated JSONObject
        JSONObject phaUserJSONObject = new JSONObject(phaUserJSONString);

        // Attempt to validate the JSON Object. Validation errors are tracked in the object
        PHAPersonValidationStatusAndMessage jsonObjectValidationStatusAndMessage = PHAPersonValidator.validateEntireUserJSONObject(phaUserJSONObject);

        // If The Validation Does Not Pass...
        if (!jsonObjectValidationStatusAndMessage.getWasDataValidationSuccess()) {

            // Create a map to hold data validation status codes and messages
            HashMap dataValidationHashMap = new HashMap<String, String>();

            // Put a custom message into the Hash Map
            dataValidationHashMap.put("validation-status", jsonObjectValidationStatusAndMessage.getDataValidationMessage());

            //Response with an error HTTP code and the validation status messages.
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dataValidationHashMap.toString());
        }

        JMSQueueProducer jmsQueueProducer = new JMSQueueProducer(host, port, queueName);
        jmsQueueProducer.sendMessage(PHAPersonHelper.transformPHAPersonToNestedJSON(PHAPersonHelper.unmarshallDataFromJSONToPHAPerson(phaUserJSONObject)).toString());

        return ResponseEntity.status(HttpStatus.OK).body("");
    }

}
