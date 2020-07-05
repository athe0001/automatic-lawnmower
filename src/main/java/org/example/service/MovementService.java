package org.example.service;

import org.example.exception.InvalidPayloadException;
import org.example.object.Direction;
import org.example.object.Field;
import org.example.object.Instruction;
import org.example.object.Lawnmower;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MovementService {
    private static final String NEW_LINE = "\n";
    private static final Pattern BLANK_PATTERN = Pattern.compile(" ");
    private static final Pattern INSTRUCTION_PATTERN = Pattern.compile("[GDA]*$");

    public String move(String[] lines) throws InvalidPayloadException {
        Field field = Field.createFieldFromString(BLANK_PATTERN.split(lines[0]));
        List<Lawnmower> lawnmowerList = new ArrayList<>();
        List<List<Instruction>> instructionList = new ArrayList<>();
        for (int i = 1; i < lines.length; i +=2) {
            Lawnmower lawnmower = Lawnmower.createLawnmowerFromString(BLANK_PATTERN.split(lines[i]), field);
            if (lawnmower == null) {
                throw new InvalidPayloadException("The lanwmower (" + lines[i] + ") cannot be placed on the field");
            }
            lawnmowerList.add(lawnmower);
            instructionList.add(createInstructionList(lines[i+1]));
        }
        for (int i = 0; i < lawnmowerList.size(); i++) {
            moveLawnmower(field, lawnmowerList.get(i), lawnmowerList, instructionList.get(i));
        }

        return lawnmowerPositionsToString(lawnmowerList);
    }

    protected void moveLawnmower(Field field, Lawnmower lawnmower, List<Lawnmower> lawnmowerList, List<Instruction> instructionList) {
        for (Instruction instruction : instructionList) {
            switch (instruction) {
                case ADVANCE:
                    if (validateMovementPossible(field, lawnmower, lawnmowerList)) {
                        lawnmower.advanceOneCase();
                    }
                    break;
                default:
                    lawnmower.rotate(instruction);
            }
        }
    }

    public void validatePayload(String[] lines) throws InvalidPayloadException {
        if (lines.length % 2 != 1) {
            //If the number of line is not odd, it means we are missing some instructions
            throw new InvalidPayloadException("The number of lines in the payload is not odd: " + lines.length);
        }

        //Validate Field object
        validateField(lines[0]);
        for (int i = 1; i < lines.length; i +=2) {
            validateLawnMowerInitialization(lines[i]);
            validateInstructions(lines[i+1]);
        }

    }

    protected void validateField(String fieldLine) throws InvalidPayloadException {
        String[] dimensions = BLANK_PATTERN.split(fieldLine);
        if (dimensions.length != 2) {
            throw new InvalidPayloadException("There is not 2 dimensions in the field initialization line: " + dimensions.length);
        }
        for (String dimension : dimensions) {
            validateInt(dimension);
        }
    }

    protected void validateLawnMowerInitialization(String lawnmowerLine) throws InvalidPayloadException {
        String[] initParameters = BLANK_PATTERN.split(lawnmowerLine);
        if (initParameters.length != 3) {
            throw new InvalidPayloadException("There is not 3 parameters in the lawnmower initialization line: " + initParameters.length);
        }
        validateInt(initParameters[0]);
        validateInt(initParameters[1]);
        validateDirection(initParameters[2]);
    }

    private void validateDirection(String string) throws InvalidPayloadException {
        if (!Direction.NORTH.getLetter().equals(string) && !Direction.SOUTH.getLetter().equals(string) && !Direction.EAST.getLetter().equals(string) && !Direction.WEST.getLetter().equals(string)) {
            throw new InvalidPayloadException("The direction is neither NORTH, SOUTH, EAST or WEST: " + string);
        }
    }

    protected void validateInstructions(String string) throws InvalidPayloadException {
        Matcher matcher = INSTRUCTION_PATTERN.matcher(string);
        if (!matcher.matches()) {
            throw new InvalidPayloadException("The instruction can only contain G, D or A: " + string);
        }
    }

    private void validateInt(String string) throws InvalidPayloadException{
        if (string == null) {
            throw new InvalidPayloadException("The string is null.");
        }

        try {
            Integer.parseInt(string);
        } catch (NumberFormatException e) {
            throw new InvalidPayloadException(string + " is not an integer.", e);
        }
    }

    protected List<Instruction> createInstructionList(String instructionString) {
        List<Instruction> instructionList = new ArrayList<>();
        for (int i = 0; i < instructionString.length() ; i++) {
            instructionList.add(Instruction.fromLetter(instructionString.charAt(i)));
        }
        return instructionList;
    }

    protected String lawnmowerPositionsToString(List<Lawnmower> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) {
                stringBuilder.append(NEW_LINE);
            }
            stringBuilder.append(list.get(i).toString());
        }
        return stringBuilder.toString();
    }

    protected boolean validateMovementPossible(Field field, Lawnmower lawnmower, List<Lawnmower> lawnmowerList) {
        Direction direction = lawnmower.getDirection();
        int newPos;
        switch (direction) {
            case NORTH:
                newPos = lawnmower.getY() + 1;
                if (newPos <= field.getTopRightY() && canMoveInTheCase(lawnmower.getX(), newPos, lawnmowerList)) {
                    return true;
                }
                break;
            case SOUTH:
                newPos = lawnmower.getY() - 1;
                if (newPos >= 0 && canMoveInTheCase(lawnmower.getX(), newPos, lawnmowerList)) {
                    return true;
                }
                break;
            case EAST:
                newPos = lawnmower.getX() + 1;
                if (newPos <= field.getTopRightX() && canMoveInTheCase(newPos, lawnmower.getY(), lawnmowerList)) {
                    return true;
                }
                break;
            case WEST:
                newPos = lawnmower.getX() - 1;
                if (newPos >= 0 && canMoveInTheCase(newPos, lawnmower.getY(), lawnmowerList)) {
                    return true;
                }
        }
        return false;
    }

    protected boolean canMoveInTheCase(int newX, int newY, List<Lawnmower> lawnmowerList) {
        boolean canMove = true;
        for (Lawnmower lawnmower : lawnmowerList) {
            if (newX == lawnmower.getX() && newY == lawnmower.getY()) {
                canMove = false;
                break;
            }
        }
        return canMove;
    }
}
