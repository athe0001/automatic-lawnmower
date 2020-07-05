package org.example.service;

import org.example.exception.InvalidPayloadException;
import org.junit.Assert;
import org.junit.Test;

public class MovementServiceTest {

    @Test
    public void testValidateField() {
        String valid = "10 5";
        String invalid1 = "5 a";
        String invalid2 = "10 10 10";

        MovementService movementService = new MovementService();

        try {
            movementService.validateField(valid);
        } catch (InvalidPayloadException e) {
            Assert.fail("InvalidPayloadException thrown even though the string is valid");
        }

        try {
            movementService.validateField(invalid1);
            Assert.fail("InvalidPayloadException thrown even though the string is invalid");
        } catch (InvalidPayloadException ignored) {
        }

        try {
            movementService.validateField(invalid2);
            Assert.fail("InvalidPayloadException thrown even though the string is invalid");
        } catch (InvalidPayloadException ignored) {
        }
    }

    @Test
    public void testValidateLawnMowerInitialization() {
        String valid = "1 1 N";
        String valid1 = "10 1 E";

        String invalid = "a 10 N";
        String invalid1 = "10 10 D";

        MovementService movementService = new MovementService();

        try {
            movementService.validateLawnMowerInitialization(valid);
        } catch (InvalidPayloadException e) {
            Assert.fail("InvalidPayloadException thrown even though the string is valid");
        }

        try {
            movementService.validateLawnMowerInitialization(valid1);
        } catch (InvalidPayloadException e) {
            Assert.fail("InvalidPayloadException thrown even though the string is valid");
        }

        try {
            movementService.validateLawnMowerInitialization(invalid);
            Assert.fail("InvalidPayloadException thrown even though the string is invalid");
        } catch (InvalidPayloadException ignored) {
        }

        try {
            movementService.validateLawnMowerInitialization(invalid1);
            Assert.fail("InvalidPayloadException thrown even though the string is invalid");
        } catch (InvalidPayloadException ignored) {
        }
    }

    @Test
    public void testValidateInstructions() {
        String valid = "GAGAGADAA";
        String invalid = "GAGAGAGAAZ";

        MovementService movementService = new MovementService();

        try {
            movementService.validateInstructions(valid);
        } catch (InvalidPayloadException e) {
            Assert.fail("InvalidPayloadException thrown even though the string is valid");
        }

        try {
            movementService.validateInstructions(invalid);
            Assert.fail("InvalidPayloadException thrown even though the string is invalid");
        } catch (InvalidPayloadException ignored) {
        }
    }

}