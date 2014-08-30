package pl.jsed.executor;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Scanner;

import pl.jsed.processors.LineProcessor;

public class Executor {

    public void run(Reader input, Writer output, LineProcessor processor) throws IOException {
        boolean firstLine = true;
        Scanner s = new Scanner(input);
        while (s.hasNextLine()) {
            String line = s.nextLine();
            processor.setLine(line);
            String newLine = processor.process();
            if (!firstLine) {
                output.append('\n');
            }
            output.append(newLine);
            firstLine = false;
        }
        s.close();
    }

}
