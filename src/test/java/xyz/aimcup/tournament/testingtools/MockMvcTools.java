package xyz.aimcup.tournament.testingtools;

import static xyz.aimcup.tournament.testingtools.PatternTestTools.UUID_PATTERN;

import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class MockMvcTools {

    public static String applySkipUuidsFilter(String jsonResponse, boolean skipUuids) {
        if (skipUuids) {
            return jsonResponse.replaceAll(UUID_PATTERN, "");
        }
        return jsonResponse;
    }
}
