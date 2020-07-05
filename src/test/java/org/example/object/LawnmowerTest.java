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
}
