package bg.sofia.uni.fmi.mjt.grep;

import java.util.regex.Pattern;

public class PatternFactory {
	private Pattern resultPattern;

	public PatternFactory(boolean flagForIgnoreCase, boolean flagForWord, String stringToFind) {

		if (flagForIgnoreCase) {
			resultPattern = Pattern.compile(stringToFind, Pattern.CASE_INSENSITIVE);
		} else if (flagForWord) {
			resultPattern = Pattern.compile("\\b" + stringToFind + "\\b");
		} else {
			resultPattern = Pattern.compile(stringToFind);
		}

	}

	public Pattern getPattern() {
		return resultPattern;
	}
}
