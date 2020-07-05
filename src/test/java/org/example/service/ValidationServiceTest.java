package org.example.service;

import org.example.exception.InvalidPayloadException;
import org.example.object.Direction;
import org.example.object.Lawnmower;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ValidationServiceTest {

    private static final ValidationService VALIDATION_SERVICE = new ValidationService();

    @Test
    public void testValidateField() {
        String valid = "10 5";
        String invalid1 = "5 a";
        String invalid2 = "10 10 10";

        try {
            VALIDATION_SERVICE.validateField(valid);
        } catch (InvalidPayloadException e) {
            Assert.fail("InvalidPayloadException thrown even though the string is valid");
        }

        try {
            VALIDATION_SERVICE.validateField(invalid1);
            Assert.fail("InvalidPayloadException thrown even though the string is invalid");
        } catch (InvalidPayloadException ignored) {
        }

        try {
            VALIDATION_SERVICE.validateField(invalid2);
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

        try {
            VALIDATION_SERVICE.validateLawnMowerInitialization(valid);
        } catch (InvalidPayloadException e) {
            Assert.fail("InvalidPayloadException thrown even though the string is valid");
        }

        try {
            VALIDATION_SERVICE.validateLawnMowerInitialization(valid1);
        } catch (InvalidPayloadException e) {
            Assert.fail("InvalidPayloadException thrown even though the string is valid");
        }

        try {
            VALIDATION_SERVICE.validateLawnMowerInitialization(invalid);
            Assert.fail("InvalidPayloadException thrown even though the string is invalid");
        } catch (InvalidPayloadException ignored) {
        }

        try {
            VALIDATION_SERVICE.validateLawnMowerInitialization(invalid1);
            Assert.fail("InvalidPayloadException thrown even though the string is invalid");
        } catch (InvalidPayloadException ignored) {
        }
    }

    @Test
    public void testValidateInstructions() {
        String valid = "GAGAGADAA";
        String invalid = "GAGAGAGAAZ";

        try {
            VALIDATION_SERVICE.validateInstructions(valid);
        } catch (InvalidPayloadException e) {
            Assert.fail("InvalidPayloadException thrown even though the string is valid");
        }

        try {
            VALIDATION_SERVICE.validateInstructions(invalid);
            Assert.fail("InvalidPayloadException thrown even though the string is invalid");
        } catch (InvalidPayloadException ignored) {
        }
    }

    @Test
    public void testCanMoveInCase(){
        List<Lawnmower> lawnmowerList = new ArrayList<>();
        lawnmowerList.add(new Lawnmower(1,1, Direction.NORTH));
        lawnmowerList.add(new Lawnmower(1,2,Direction.EAST));

        Assert.assertFalse(VALIDATION_SERVICE.canMoveInTheCase(1,1, lawnmowerList));
        Assert.assertTrue(VALIDATION_SERVICE.canMoveInTheCase(1,3, lawnmowerList));
    }
}
