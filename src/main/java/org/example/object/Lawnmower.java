package org.example.object;

public class Lawnmower {
    private static final String BLANK = " ";
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

    public void advanceOneCase(Field field) {
        switch (this.direction) {
            case NORTH:
                if (this.y + 1 <= field.getTopRightY()) {
                    moveAlongY(true);
                }
                break;
            case SOUTH:
                if (this.y - 1 >= 0) {
                    moveAlongY(false);
                }
                break;
            case EAST:
                if (this.x + 1 <= field.getTopRightX()) {
                    moveAlongX(true);
                }
                break;
            case WEST:
                if (this.x - 1 >= 0) {
                    moveAlongX(false);
                }
        }
    }

    public void rotate(Instruction instruction) {
        switch (instruction) {
            case LEFT:
                rotate(true);
                break;
            case RIGHT:
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

    private void moveAlongY(boolean isItTowardNorth) {
        this.y = isItTowardNorth ? this.y + 1 : this.y -1;
    }

    private void moveAlongX(boolean isItTowardEast) {
        this.x = isItTowardEast ? this.x + 1 : this.x -1;
    }

    public static Lawnmower createLawnmowerFromString(String[] lawnmowerInitParameter, Field field) {
        int lawnmowerX = Integer.parseInt(lawnmowerInitParameter[0]);
        int lawnmowerY = Integer.parseInt(lawnmowerInitParameter[1]);
        Direction direction = Direction.fromLetter(lawnmowerInitParameter[2]);
        if (lawnmowerX <= field.getTopRightX() && lawnmowerY <= field.getTopRightY() && direction != null) {
            return new Lawnmower(lawnmowerX, lawnmowerY, direction);
        }
        return null;
    }

    public String toString() {
        return this.x + BLANK + this.y +BLANK +this.direction.getLetter();
    }

}
