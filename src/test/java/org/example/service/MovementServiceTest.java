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

    private static final MovementService  MOVEMENT_SERVICE = new MovementService(new ValidationService());

    @Test
    public void testMove() throws InvalidPayloadException {
        String[] lines = new String[]{"5 5", "1 2 N", "GAGAGAGAA", "3 3 E", "AADAADADDA"};
        String result = MOVEMENT_SERVICE.move(lines);

        Assert.assertEquals("1 3 N\n5 1 E", result);
    }

    @Test(expected = InvalidPayloadException.class)
    public void testMoveInvalidLawnmowerPosition() throws InvalidPayloadException {
        String[] lines = new String[]{"5 5", "6 2 N", "GAGAGAGAA"};
        MOVEMENT_SERVICE.move(lines);
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

        String result = "1 1 E\n2 5 N";
        Assert.assertEquals(result, MOVEMENT_SERVICE.lawnmowerPositionsToString(lawnmowerList));
    }

}
