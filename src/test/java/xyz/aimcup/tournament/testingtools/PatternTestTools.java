package xyz.aimcup.tournament.testingtools;

import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class PatternTestTools {

    public final static String UUID_PATTERN = "([0-9a-fA-F]{8}\\b-[0-9a-fA-F]{4}"
        + "\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{12})";
}
