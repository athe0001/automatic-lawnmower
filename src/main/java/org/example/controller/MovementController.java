package org.example.controller;

import org.example.exception.InvalidPayloadException;
import org.example.service.MovementService;
import org.example.service.ValidationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovementController {

    private MovementService movementService;
    private ValidationService validationService;

    public MovementController(MovementService movementService, ValidationService validationService) {
        this.movementService = movementService;
        this.validationService = validationService;
    }

    @PostMapping("/moveSimulation")
    public String moveSimulation(@RequestBody String payload) throws InvalidPayloadException {
        String[] lines = payload.split(System.getProperty("line.separator"));

        validationService.validatePayload(lines);
        return movementService.move(lines);
    }


}
