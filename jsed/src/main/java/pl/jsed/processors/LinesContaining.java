package pl.jsed.processors;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinesContaining extends LineProcessor {

    Pattern pattern = null;

    @Override
    public Optional<String> process() {
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            return Optional.of(line);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public String getName() {
        return "linesContaining";
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
