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
import pl.jsed.processors.manuals.LineProcessorManual;
import pl.jsed.processors.manuals.ManualUnavilable;

public class JSed {
    public static void main(String[] args) {

        if (args.length == 0) {
            showDefaultMessage();
        } else if (args[0].equalsIgnoreCase("manual")) {
            showHelp(args);
        } else {
            runExector(args);
        }
    }

    protected static void runExector(String[] args) {
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

    private static void showHelp(String[] args) {
        try {
            if (args.length == 1) {
                showAvailableExecutors();
            } else {
                LineProcessorManual manual = selectExecutorManual(args);
                System.out.println(manual.getManual());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static LineProcessorManual selectExecutorManual(String[] args) throws InstantiationException, IllegalAccessException {
        Reflections reflections = new Reflections("pl.jsed.processors.manuals");
        Set<Class<? extends LineProcessorManual>> subTypes = reflections.getSubTypesOf(LineProcessorManual.class);

        for (Class<? extends LineProcessorManual> processorType : subTypes) {
            LineProcessorManual processor = (LineProcessorManual) processorType.newInstance();
            if (processor.getName().equalsIgnoreCase(args[1])) {
                return processor;
            }
        }

        return new ManualUnavilable();
    }

    private static void showAvailableExecutors() throws InstantiationException, IllegalAccessException {
        Reflections reflections = new Reflections("pl.jsed.processors");
        Set<Class<? extends LineProcessor>> subTypes = reflections.getSubTypesOf(LineProcessor.class);

        System.out.println("List of all available executors:");
        for (Class<? extends LineProcessor> processorType : subTypes) {
            LineProcessor processor = processorType.newInstance();
            System.out.println(processor.getName());
        }

    }

    private static void showDefaultMessage() {
        System.out.println("Usage: jsed [EXECUTOR NAME] [FILE PATH] ...\n");
        System.out.println("List of all available executors: jsed manual\n");
        System.out.println("Executor manual: jsed manual [EXECUTOR NAME]");
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
