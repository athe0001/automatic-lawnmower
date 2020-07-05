package org.example.object;

public class Field {

    private int topRightX;
    private int topRightY;

    public Field(int topRightX, int topRightY) {
        this.topRightX = topRightX;
        this.topRightY = topRightY;
    }

    public int getTopRightX() {
        return topRightX;
    }

    public void setTopRightX(int topRightX) {
        this.topRightX = topRightX;
    }

    public int getTopRightY() {
        return topRightY;
    }

    public void setTopRightY(int topRightY) {
        this.topRightY = topRightY;
    }
}
