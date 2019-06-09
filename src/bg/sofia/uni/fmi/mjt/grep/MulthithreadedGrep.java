package bg.sofia.uni.fmi.mjt.grep;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MulthithreadedGrep {
	private Pattern patternForGrep;
	private List<String> allFiles;
	private String path;
	private int numberOfThreads;
	private PrintStream streamForPrinting;

	public MulthithreadedGrep(Pattern patternForGrep, String path, int numberOfThreads, PrintStream streamForPrinting) {
		this.patternForGrep = patternForGrep;
		this.path = path;
		getAllFiles();
		this.numberOfThreads = numberOfThreads;
		this.streamForPrinting = streamForPrinting;
	}

	private void getAllFiles() {
		try {
			allFiles = Files.walk(getPath()).filter(Files::isRegularFile).map(e -> e.toString())
					.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Path getPath() {
		return Paths.get(path);
	}

	public void execute() {
		ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
		for (String path : allFiles) {
			Runnable worker = new Grep(path, patternForGrep, streamForPrinting);
			executor.execute(worker);
		}
		executor.shutdown();
		while (!executor.isTerminated()) {
		}
	}

}
