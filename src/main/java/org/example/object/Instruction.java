package org.example.object;

public enum  Instruction {
    ADVANCE('A'),
    LEFT('G'),
    RIGHT('D');

    private char letter;

    Instruction(char letter) {
        this.letter = letter;
    }

    public char getLetter() {
        return this.letter;
    }

    public static Instruction fromLetter(char letter) {
        for (Instruction instruction : Instruction.values()) {
            if (instruction.letter == letter) {
                return instruction;
            }
        }
        return null;
    }
}
