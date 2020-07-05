package org.example.service;

import org.example.common.Constants;
import org.example.exception.InvalidPayloadException;
import org.example.object.Direction;
import org.example.object.Field;
import org.example.object.Lawnmower;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;

@Service
public class ValidationService {

    /**
     * Check that the payload is valid (all characters in it are valid, format, etc.
     * Does not check if the lawnmower can be placed in the field.
     * @param lines
     * @throws InvalidPayloadException
     */
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

    /**
     * Check that the field line is valid
     * @param fieldLine
     * @throws InvalidPayloadException
     */
    protected void validateField(String fieldLine) throws InvalidPayloadException {
        String[] dimensions = Constants.BLANK_PATTERN.split(fieldLine);
        if (dimensions.length != 2) {
            throw new InvalidPayloadException("There is not 2 dimensions in the field initialization line: " + dimensions.length);
        }
        for (String dimension : dimensions) {
            validateInt(dimension);
        }
    }

    /**
     * Check that the lawnmower lines are valid
     * @param lawnmowerLine
     * @throws InvalidPayloadException
     */
    protected void validateLawnMowerInitialization(String lawnmowerLine) throws InvalidPayloadException {
        String[] initParameters = Constants.BLANK_PATTERN.split(lawnmowerLine);
        if (initParameters.length != 3) {
            throw new InvalidPayloadException("There is not 3 parameters in the lawnmower initialization line: " + initParameters.length);
        }
        validateInt(initParameters[0]);
        validateInt(initParameters[1]);
        validateDirection(initParameters[2]);
    }

    /**
     * Check that the direction is valid
     * @param string
     * @throws InvalidPayloadException
     */
    private void validateDirection(String string) throws InvalidPayloadException {
        if (!Direction.NORTH.getLetter().equals(string) && !Direction.SOUTH.getLetter().equals(string) && !Direction.EAST.getLetter().equals(string) && !Direction.WEST.getLetter().equals(string)) {
            throw new InvalidPayloadException("The direction is neither NORTH, SOUTH, EAST or WEST: " + string);
        }
    }

    /**
     * Check that all instructions are valid
     * @param string
     * @throws InvalidPayloadException
     */
    protected void validateInstructions(String string) throws InvalidPayloadException {
        Matcher matcher = Constants.INSTRUCTION_PATTERN.matcher(string);
        if (!matcher.matches()) {
            throw new InvalidPayloadException("The instruction can only contain G, D or A: " + string);
        }
    }

    /**
     * Validate that the string is an int
     * @param string
     * @throws InvalidPayloadException
     */
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

    /**
     * Check that the movement of the lawnmower is possible
     * @param field
     * @param lawnmower
     * @param lawnmowerList
     * @return whether the lawnmower can advance
     */
    protected boolean checkMovementPossible(Field field, Lawnmower lawnmower, List<Lawnmower> lawnmowerList) {
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

    /**
     * Check if the case is not occupied that another lawnmower
     * @param newX
     * @param newY
     * @param lawnmowerList
     * @return whether the case is empty
     */
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
