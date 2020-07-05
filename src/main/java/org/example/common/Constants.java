package org.example.common;

import java.util.regex.Pattern;

public class Constants {

    public static final String NEW_LINE = "\n";
    public static final Pattern BLANK_PATTERN = Pattern.compile(" ");
    public static final Pattern INSTRUCTION_PATTERN = Pattern.compile("[GDA]*$");
}
