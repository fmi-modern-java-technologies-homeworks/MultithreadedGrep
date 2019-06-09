package bg.sofia.uni.fmi.mjt.grep;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Arguments {
    private String stringToFind;
    private String pathToDirectory;
    private int numberOfThreads;
    private PrintStream streamForPrinting;

    public Arguments(String[] arguments, int startIndex) {
        int index = startIndex;
        stringToFind = arguments[index++];
        pathToDirectory = arguments[index++];
        numberOfThreads = Integer.parseInt(arguments[index++]);
        if (arguments.length == (index + 1)) {
            try {
                streamForPrinting = new PrintStream(arguments[index]);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            streamForPrinting = System.out;
        }
    }

    public String getStringToFind() {
        return stringToFind;
    }

    public String getPathToDirectory() {
        return pathToDirectory;
    }

    public int getNumberOfThreads() {
        return numberOfThreads;
    }

    public PrintStream getStreamForPrinting() {
        return streamForPrinting;
    }

}
