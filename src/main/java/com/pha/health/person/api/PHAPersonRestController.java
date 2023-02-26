package com.pha.health.person.api;

import com.pha.health.person.model.PHAPersonHelper;
import com.pha.health.person.model.PHAPersonValidator;
import com.pha.health.validation.ValidationStatusAndMessage;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
     * Validates the JSON String representation of a PHA Person.
     */
    @PostMapping("/validate")
    public ResponseEntity<String> validatePHAUserJSON(@RequestBody String phaUserJSONString) {


        JSONObject phaUserJSONObject = new JSONObject(phaUserJSONString);

        ValidationStatusAndMessage jsonObjectValidationStatusAndMessage = PHAPersonValidator.validateUserJSONObject(phaUserJSONObject);

        if (!jsonObjectValidationStatusAndMessage.getValidationStatus()) {

            Map map = new HashMap<String, String>();

            map.put("status", jsonObjectValidationStatusAndMessage.getValidationNotification());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map.toString());

        }

        return ResponseEntity.status(HttpStatus.OK).body(
                PHAPersonHelper.toJSON(
                        PHAPersonHelper.loadFrom(phaUserJSONObject)
                ).toString(3));
    }

}
