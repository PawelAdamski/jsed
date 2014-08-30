package pl.jsed;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;

import pl.jsed.executor.Executor;
import pl.jsed.processors.LineProcessor;
import pl.jsed.processors.Replace;
import pl.jsed.processors.ReplaceAll;

public class App {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));

        LineProcessor processor = selectProcessor(args);

        for (int i = 3; i < args.length; i++) {
            File f = new File(args[i]);
            try {

                String content = FileUtils.readFileToString(f);
                StringReader input = new StringReader(content);
                FileWriter output = new FileWriter(f);

                new Executor().run(input, output, processor);
                output.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static LineProcessor selectProcessor(String[] args) {
        if (args[0].equalsIgnoreCase("replaceall")) {
            return new ReplaceAll(args[1], args[2]);
        } else if (args[0].equalsIgnoreCase("replace")) {
            return new Replace(args[1], args[2]);
        }
        throw new RuntimeException("Processor " + args[0] + " not found.");
    }
}
