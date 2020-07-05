package org.example.controller;

import org.example.exception.InvalidPayloadException;
import org.example.service.MovementService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class MovementController {

    private MovementService movementService;

    public MovementController(MovementService movementService) {
        this.movementService = movementService;
    }

    @PostMapping("/moveSimulation")
    public String moveSimulation(@RequestBody String payload) throws InvalidPayloadException {
        String[] lines = payload.split(System.getProperty("line.separator"));

        movementService.validatePayload(lines);
        return movementService.move();
    }


}
