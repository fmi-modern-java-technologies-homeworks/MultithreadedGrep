package bg.sofia.uni.fmi.mjt.grep;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
	private static final int FIRST = 1;
	private static final int SECOND = 2;

	public static void main(String[] args) {
		boolean flagForIgnoreCase = false;
		boolean flagForWord = false;

		Scanner scanner = new Scanner(System.in);
		String line = scanner.nextLine();

		String[] input = line.split(" ");

		Arguments arguments;

		if (input[FIRST].equals("-w")) {
			flagForWord = true;
			arguments = new Arguments(input, SECOND);
		} else if (input[FIRST].equals("-i")) {
			flagForIgnoreCase = true;
			arguments = new Arguments(input, SECOND);

		} else {
			arguments = new Arguments(input, FIRST);
		}

		Pattern patternForGrep = (new PatternFactory(flagForIgnoreCase, flagForWord, arguments.getStringToFind()))
				.getPattern();

		(new MulthithreadedGrep(patternForGrep, arguments.getPathToDirectory(), arguments.getNumberOfThreads(),
				arguments.getStreamForPrinting())).execute();

		arguments.getStreamForPrinting().close();
	}

}
