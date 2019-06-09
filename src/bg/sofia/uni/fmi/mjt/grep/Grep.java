package bg.sofia.uni.fmi.mjt.grep;

import java.io.*;
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
        setBufferedReader(path);
        this.streamForPrinting = streamForPrinting;
        System.setOut(this.streamForPrinting);
    }

    private void setBufferedReader(String pathOfASingleFile) {
        try {
            bufferedReader = new BufferedReader(new FileReader(pathOfASingleFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        execute();
    }

    private void execute() {
        try {
            findWords();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void findWords() throws IOException {
        int lineNumber = 1;
        while (true) {
            final String line = bufferedReader.readLine();

            if (line == null) {
                break;
            }

            Matcher matcherOfGrep = patternOfGrep.matcher(line);
            if (matcherOfGrep.find()) {
                print(lineNumber, line);
            }
            ++lineNumber;
        }
    }

    private void print(int lineNumber, String lineText) {
        String lineMessage = path + ":" + lineNumber + ":" + lineText;
        System.out.println(lineMessage);
    }
}
