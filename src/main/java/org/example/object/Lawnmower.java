package org.example.object;

public class Lawnmower {
    private int x;
    private int y;
    private Direction direction;

    public Lawnmower(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }


    public void rotate(String rotation) {
        switch (rotation) {
            case "G":
                rotate(true);
                break;
            case "D":
                rotate(false);
                break;
            default:
                //TODO log doing nothing
                break;
        }
    }

    private void rotate(boolean isRotateLeft) {
        switch (this.direction) {
            case NORTH:
                if (isRotateLeft) {
                    this.direction = Direction.WEST;
                } else {
                    this.direction = Direction.EAST;
                }
                break;
            case SOUTH:
                if (isRotateLeft) {
                    this.direction = Direction.EAST;
                } else {
                    this.direction = Direction.WEST;
                }
                break;
            case EAST:
                if (isRotateLeft) {
                    this.direction = Direction.NORTH;
                } else {
                    this.direction = Direction.SOUTH;
                }
                break;
            case WEST:
                if (isRotateLeft) {
                    this.direction = Direction.SOUTH;
                } else {
                    this.direction = Direction.NORTH;
                }
                break;
        }
    }

}
