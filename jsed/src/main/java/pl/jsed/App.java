package pl.jsed;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.reflections.Reflections;

import pl.jsed.executor.Executor;
import pl.jsed.processors.LineProcessor;

public class App {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));

        try {
            LineProcessor processor = selectProcessor(args);
            String[] processorArguments = Arrays.copyOfRange(args, 1, processor.getNumberOfArguments() + 1);
            processor.setArguments(processorArguments);

            for (int i = processor.getNumberOfArguments() + 1; i < args.length; i++) {
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
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static LineProcessor selectProcessor(String[] args) throws InstantiationException, IllegalAccessException {

        Reflections reflections = new Reflections("pl.jsed.processors");
        Set<Class<? extends LineProcessor>> subTypes = reflections.getSubTypesOf(LineProcessor.class);

        for (Class<? extends LineProcessor> processorType : subTypes) {
            LineProcessor processor = processorType.newInstance();
            if (processor.getName().equalsIgnoreCase(args[0])) {
                return processor;
            }
        }

        throw new RuntimeException("Processor " + args[0] + " not found.");
    }
}
