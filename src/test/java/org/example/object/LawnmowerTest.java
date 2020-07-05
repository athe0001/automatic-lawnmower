package org.example.object;

import org.junit.Assert;
import org.junit.Test;

public class LawnmowerTest {

    @Test
    public void testRotation() {
        Lawnmower lawnmower = new Lawnmower(0, 0, Direction.NORTH);
       Instruction instructionLeft = Instruction.fromLetter('G');

        lawnmower.rotate(instructionLeft);
        Assert.assertEquals(Direction.WEST, lawnmower.getDirection());

        lawnmower.rotate(instructionLeft);
        Assert.assertEquals(Direction.SOUTH, lawnmower.getDirection());

        lawnmower.rotate(instructionLeft);
        Assert.assertEquals(Direction.EAST, lawnmower.getDirection());

        lawnmower.rotate(instructionLeft);
        Assert.assertEquals(Direction.NORTH, lawnmower.getDirection());

        Instruction instructionRight = Instruction.fromLetter('D');
        lawnmower.rotate(instructionRight);
        Assert.assertEquals(Direction.EAST, lawnmower.getDirection());

        lawnmower.rotate(instructionRight);
        Assert.assertEquals(Direction.SOUTH, lawnmower.getDirection());

        lawnmower.rotate(instructionRight);
        Assert.assertEquals(Direction.WEST, lawnmower.getDirection());

        lawnmower.rotate(instructionRight);
        Assert.assertEquals(Direction.NORTH, lawnmower.getDirection());
    }

    @Test
    public void testMove(){
        Field field = new Field(1,1);
        Lawnmower lawnmower = new Lawnmower(0,0, Direction.NORTH);

        lawnmower.advanceOneCase();
        Assert.assertEquals(0, lawnmower.getX());
        Assert.assertEquals(1, lawnmower.getY());

        lawnmower.setDirection(Direction.EAST);
        lawnmower.advanceOneCase();
        Assert.assertEquals(1, lawnmower.getX());
        Assert.assertEquals(1, lawnmower.getY());

        lawnmower.setDirection(Direction.SOUTH);
        lawnmower.advanceOneCase();
        Assert.assertEquals(1, lawnmower.getX());
        Assert.assertEquals(0, lawnmower.getY());

        lawnmower.setDirection(Direction.WEST);
        lawnmower.advanceOneCase();
        Assert.assertEquals(0, lawnmower.getX());
        Assert.assertEquals(0, lawnmower.getY());
    }
    @Test
    public void testCreateLawnmowerFromString() {
        Field field = new Field(1,1);
        String param = "1 1 N";

        Lawnmower lawnmower = Lawnmower.createLawnmowerFromString(param.split(" "), field);
        Assert.assertNotNull(lawnmower);
        Assert.assertEquals(1, lawnmower.getX());
        Assert.assertEquals(1, lawnmower.getY());
        Assert.assertEquals(Direction.NORTH, lawnmower.getDirection());

        String paramInvalid = "1 2 N";
        Lawnmower lawnmower1 = Lawnmower.createLawnmowerFromString(paramInvalid.split(" "), field);
        Assert.assertNull(lawnmower1);
    }
}
