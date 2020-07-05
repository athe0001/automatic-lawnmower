package org.example.service;

import org.example.exception.InvalidPayloadException;
import org.example.object.Direction;
import org.example.object.Instruction;
import org.example.object.Lawnmower;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MovementServiceTest {

    private static final MovementService  MOVEMENT_SERVICE = new MovementService();

    @Test
    public void testMove() throws InvalidPayloadException {
        String[] lines = new String[]{"5 5", "1 2 N", "GAGAGAGAA", "3 3 E", "AADAADADDA"};
        String result = MOVEMENT_SERVICE.move(lines);

        Assert.assertEquals("1 3 N\n5 1 E\n", result);
    }

    @Test(expected = InvalidPayloadException.class)
    public void testMoveInvalidLawnmowerPosition() throws InvalidPayloadException {
        String[] lines = new String[]{"5 5", "6 2 N", "GAGAGAGAA"};
        MOVEMENT_SERVICE.move(lines);
    }


    @Test
    public void testValidateField() {
        String valid = "10 5";
        String invalid1 = "5 a";
        String invalid2 = "10 10 10";

        try {
            MOVEMENT_SERVICE.validateField(valid);
        } catch (InvalidPayloadException e) {
            Assert.fail("InvalidPayloadException thrown even though the string is valid");
        }

        try {
            MOVEMENT_SERVICE.validateField(invalid1);
            Assert.fail("InvalidPayloadException thrown even though the string is invalid");
        } catch (InvalidPayloadException ignored) {
        }

        try {
            MOVEMENT_SERVICE.validateField(invalid2);
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
            MOVEMENT_SERVICE.validateLawnMowerInitialization(valid);
        } catch (InvalidPayloadException e) {
            Assert.fail("InvalidPayloadException thrown even though the string is valid");
        }

        try {
            MOVEMENT_SERVICE.validateLawnMowerInitialization(valid1);
        } catch (InvalidPayloadException e) {
            Assert.fail("InvalidPayloadException thrown even though the string is valid");
        }

        try {
            MOVEMENT_SERVICE.validateLawnMowerInitialization(invalid);
            Assert.fail("InvalidPayloadException thrown even though the string is invalid");
        } catch (InvalidPayloadException ignored) {
        }

        try {
            MOVEMENT_SERVICE.validateLawnMowerInitialization(invalid1);
            Assert.fail("InvalidPayloadException thrown even though the string is invalid");
        } catch (InvalidPayloadException ignored) {
        }
    }

    @Test
    public void testValidateInstructions() {
        String valid = "GAGAGADAA";
        String invalid = "GAGAGAGAAZ";

        try {
            MOVEMENT_SERVICE.validateInstructions(valid);
        } catch (InvalidPayloadException e) {
            Assert.fail("InvalidPayloadException thrown even though the string is valid");
        }

        try {
            MOVEMENT_SERVICE.validateInstructions(invalid);
            Assert.fail("InvalidPayloadException thrown even though the string is invalid");
        } catch (InvalidPayloadException ignored) {
        }
    }

    @Test
    public void testCreateInstructionList() {
        String instruction = "GAGAGADAA";

        List<Instruction> instructionList = MOVEMENT_SERVICE.createInstructionList(instruction);
        Assert.assertEquals(9, instructionList.size());
        Assert.assertEquals(Instruction.LEFT, instructionList.get(0));
        Assert.assertEquals(Instruction.ADVANCE, instructionList.get(1));
        Assert.assertEquals(Instruction.LEFT, instructionList.get(2));
        Assert.assertEquals(Instruction.ADVANCE, instructionList.get(3));
        Assert.assertEquals(Instruction.LEFT, instructionList.get(4));
        Assert.assertEquals(Instruction.ADVANCE, instructionList.get(5));
        Assert.assertEquals(Instruction.RIGHT, instructionList.get(6));
        Assert.assertEquals(Instruction.ADVANCE, instructionList.get(7));
        Assert.assertEquals(Instruction.ADVANCE, instructionList.get(8));
    }

    @Test
    public void testLawnmowerPositionsToString() {
        List<Lawnmower> lawnmowerList = new ArrayList<>();
        lawnmowerList.add(new Lawnmower(1,1, Direction.EAST));
        lawnmowerList.add(new Lawnmower(2,5,Direction.NORTH));

        String result = "1 1 E\n2 5 N\n";
        Assert.assertEquals(result, MOVEMENT_SERVICE.lawnmowerPositionsToString(lawnmowerList));
    }

    @Test
    public void testCanMoveInCase(){
        List<Lawnmower> lawnmowerList = new ArrayList<>();
        lawnmowerList.add(new Lawnmower(1,1, Direction.NORTH));
        lawnmowerList.add(new Lawnmower(1,2,Direction.EAST));

        Assert.assertFalse(MOVEMENT_SERVICE.canMoveInTheCase(1,1, lawnmowerList));
        Assert.assertTrue(MOVEMENT_SERVICE.canMoveInTheCase(1,3, lawnmowerList));
    }

}
