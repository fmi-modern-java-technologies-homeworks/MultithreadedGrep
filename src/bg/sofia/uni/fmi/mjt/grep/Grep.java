package bg.sofia.uni.fmi.mjt.grep;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grep implements Runnable {

	private String path;
	private BufferedReader bufferedReader;
	private Pattern patternOfGrep;
	private PrintStream streamForPrinting;

	public Grep(String path, Pattern patternOfGrep, PrintStream streamForPrinting) {
		this.path = path;
		this.patternOfGrep = patternOfGrep;
		read(path);
		this.streamForPrinting = streamForPrinting;
		System.setOut(this.streamForPrinting);
	}

	private void read(String pathOfASingleFile) {
		try {
			bufferedReader = new BufferedReader(new FileReader(pathOfASingleFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void print(int lineNumber, String lineText) {
		System.out.println(path + ":" + lineNumber + ":" + lineText);
	}

	private void findWords() {
		int lineNumber = 1;
		String lineText;
		try {
			while ((lineText = bufferedReader.readLine()) != null) {
				Matcher matcherOfGrep = patternOfGrep.matcher(lineText);
				if (matcherOfGrep.find()) {
					print(lineNumber, lineText);
				}
				++lineNumber;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		findWords();
	}

}
