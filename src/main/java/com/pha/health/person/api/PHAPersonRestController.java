package com.pha.health.person.api;

import com.pha.health.person.model.PHAPersonValidator;
import com.pha.health.validation.ValidationStatusAndMessage;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * Restful PHA Person Validation API Controller for the PHA Person API
 */
@CrossOrigin
@RestController
@RequestMapping("/pha/person")
public class PHAPersonRestController {

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
    public ResponseEntity<String> validatePHAUserJSON(@RequestBody String phaUserJSONString) {

        // Create a populated JSONObject from the JSON String
        JSONObject phaUserJSONObject = new JSONObject(phaUserJSONString);

        // Attempt to validate the JSON Object. Validation errors are tracked in the object
        ValidationStatusAndMessage jsonObjectValidationStatusAndMessage = PHAPersonValidator.validateEntireUserJSONObject(phaUserJSONObject);

        // If The Validation Does Not Pass...
        if (!jsonObjectValidationStatusAndMessage.getWasDataValidationSuccess()) {

            // Create a map to hold data validation status codes and messages
            HashMap dataValidationHashMap = new HashMap<String, String>();

            // Put a custom message into the Hash Map
            dataValidationHashMap.put("validation-status", jsonObjectValidationStatusAndMessage.getDataValidationMessage());

            //Response with an error HTTP code and the validation status messages.
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dataValidationHashMap.toString());
        }

        // On successful data validation, return HTTP Status OK (200)
        return ResponseEntity.status(HttpStatus.OK).body("");
    }

}
