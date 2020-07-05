package org.example.object;

public enum Direction {
    NORTH("N"),
    SOUTH("S"),
    EAST("E"),
    WEST("W");

    private String letter;

    Direction(String letter) {
        this.letter = letter;
    }

    public String getLetter() {
        return this.letter;
    }

    public static Direction fromLetter(String letter) {
        for (Direction direction : Direction.values()) {
            if (direction.letter.equalsIgnoreCase(letter)) {
                return direction;
            }
        }
        return null;
    }

}
