package pl.jsed.processors;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Upper extends LineProcessor {

    private Pattern pattern;

    @Override
    public Optional<String> process() {
        StringBuilder result = new StringBuilder(line);
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            do {
                int start = matcher.start();
                int end = matcher.end();
                upper(result, start, end);
            } while (matcher.find(matcher.start(0) + 1));
        }
        return Optional.of(result.toString());
    }

    private void upper(StringBuilder result, int start, int end) {
        for (int i = start; i < end; i++) {
            char upperChar = Character.toUpperCase(result.charAt(i));
            result.setCharAt(i, upperChar);
        }
    }

    @Override
    public String getName() {
        return "upper";
    }

    @Override
    public int getNumberOfArguments() {
        return 1;
    }

    @Override
    public void setArguments(String... args) {
        this.pattern = Pattern.compile(args[0]);
    }

}
