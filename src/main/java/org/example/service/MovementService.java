package org.example.service;

import org.example.common.Constants;
import org.example.exception.InvalidPayloadException;
import org.example.object.Field;
import org.example.object.Instruction;
import org.example.object.Lawnmower;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovementService {
    private ValidationService validationService;

    public MovementService(ValidationService validationService) {
        this.validationService = validationService;
    }

    public String move(String[] lines) throws InvalidPayloadException {
        Field field = Field.createFieldFromString(Constants.BLANK_PATTERN.split(lines[0]));
        List<Lawnmower> lawnmowerList = new ArrayList<>();
        List<List<Instruction>> instructionList = new ArrayList<>();
        for (int i = 1; i < lines.length; i +=2) {
            Lawnmower lawnmower = Lawnmower.createLawnmowerFromString(Constants.BLANK_PATTERN.split(lines[i]), field);
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

    private void moveLawnmower(Field field, Lawnmower lawnmower, List<Lawnmower> lawnmowerList, List<Instruction> instructionList) {
        for (Instruction instruction : instructionList) {
            switch (instruction) {
                case ADVANCE:
                    if (validationService.validateMovementPossible(field, lawnmower, lawnmowerList)) {
                        lawnmower.advanceOneCase();
                    }
                    break;
                default:
                    lawnmower.rotate(instruction);
            }
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
                stringBuilder.append(Constants.NEW_LINE);
            }
            stringBuilder.append(list.get(i).toString());
        }
        return stringBuilder.toString();
    }
}
