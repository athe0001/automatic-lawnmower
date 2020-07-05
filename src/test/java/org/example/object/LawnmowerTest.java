package org.example.object;

import org.junit.Assert;
import org.junit.Test;

public class LawnmowerTest {

    @Test
    public void testRotation() {
        Lawnmower lawnmower = new Lawnmower(0, 0, Direction.NORTH);
        String rotateLeft = "G";

        lawnmower.rotate(rotateLeft);
        Assert.assertEquals(Direction.WEST, lawnmower.getDirection());

        lawnmower.rotate(rotateLeft);
        Assert.assertEquals(Direction.SOUTH, lawnmower.getDirection());

        lawnmower.rotate(rotateLeft);
        Assert.assertEquals(Direction.EAST, lawnmower.getDirection());

        lawnmower.rotate(rotateLeft);
        Assert.assertEquals(Direction.NORTH, lawnmower.getDirection());

        String rotateRight = "D";
        lawnmower.rotate(rotateRight);
        Assert.assertEquals(Direction.EAST, lawnmower.getDirection());

        lawnmower.rotate(rotateRight);
        Assert.assertEquals(Direction.SOUTH, lawnmower.getDirection());

        lawnmower.rotate(rotateRight);
        Assert.assertEquals(Direction.WEST, lawnmower.getDirection());

        lawnmower.rotate(rotateRight);
        Assert.assertEquals(Direction.NORTH, lawnmower.getDirection());

        //Do nothing
        lawnmower.rotate("TEST");
        Assert.assertEquals(Direction.NORTH, lawnmower.getDirection());
    }

    @Test
    public void testMove(){
        Field field = new Field(1,1);
        Lawnmower lawnmower = new Lawnmower(0,0, Direction.NORTH);

        lawnmower.advanceOneCase(field);
        Assert.assertEquals(0, lawnmower.getX());
        Assert.assertEquals(1, lawnmower.getY());

        //Do nothing since end of field
        lawnmower.advanceOneCase(field);
        Assert.assertEquals(0, lawnmower.getX());
        Assert.assertEquals(1, lawnmower.getY());

        lawnmower.setDirection(Direction.EAST);
        lawnmower.advanceOneCase(field);
        Assert.assertEquals(1, lawnmower.getX());
        Assert.assertEquals(1, lawnmower.getY());

        //Do nothing since end of field
        lawnmower.advanceOneCase(field);
        Assert.assertEquals(1, lawnmower.getX());
        Assert.assertEquals(1, lawnmower.getY());

        lawnmower.setDirection(Direction.SOUTH);
        lawnmower.advanceOneCase(field);
        Assert.assertEquals(1, lawnmower.getX());
        Assert.assertEquals(0, lawnmower.getY());

        //Do nothing since end of field
        lawnmower.advanceOneCase(field);
        Assert.assertEquals(1, lawnmower.getX());
        Assert.assertEquals(0, lawnmower.getY());

        lawnmower.setDirection(Direction.WEST);
        lawnmower.advanceOneCase(field);
        Assert.assertEquals(0, lawnmower.getX());
        Assert.assertEquals(0, lawnmower.getY());

        //Do nothing since end of field
        lawnmower.advanceOneCase(field);
        Assert.assertEquals(0, lawnmower.getX());
        Assert.assertEquals(0, lawnmower.getY());
    }
}
