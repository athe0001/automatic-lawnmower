package org.example.service;

import org.example.exception.InvalidPayloadException;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MovementService {
    private static final Pattern BLANK = Pattern.compile(" ");
    private static final Pattern INSTRUCTION_PATTERN = Pattern.compile("[GDA]*$");
    private static final String NORTH = "N";
    private static final String SOUTH = "S";
    private static final String EAST = "E";
    private static final String WEST = "W";

    public String move() {
        return "";
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
        String[] dimensions = BLANK.split(fieldLine);
        if (dimensions.length != 2) {
            throw new InvalidPayloadException("There is not 2 dimensions in the field initialization line: " + dimensions.length);
        }
        for (String dimension : dimensions) {
            validateInt(dimension);
        }
    }

    protected void validateLawnMowerInitialization(String lawnmowerLine) throws InvalidPayloadException {
        String[] initParameters = BLANK.split(lawnmowerLine);
        if (initParameters.length != 3) {
            throw new InvalidPayloadException("There is not 3 parameters in the lawnmower initialization line: " + initParameters.length);
        }
        validateInt(initParameters[0]);
        validateInt(initParameters[1]);
        validateDirection(initParameters[2]);
    }

    private void validateDirection(String string) throws InvalidPayloadException {
        if (!NORTH.equals(string) && !SOUTH.equals(string) && !EAST.equals(string) && !WEST.equals(string)) {
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

}
