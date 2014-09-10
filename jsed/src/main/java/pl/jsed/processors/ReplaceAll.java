package pl.jsed.processors;

import java.util.Optional;

public class ReplaceAll extends LineProcessor {

    private String regex;
    private String replacement;

    @Override
    public Optional<String> process() {
        return Optional.of(line.replaceAll(regex, replacement));
    }

    @Override
    public String getName() {
        return "replaceAll";
    }

    @Override
    public int getNumberOfArguments() {
        return 2;
    }

    @Override
    public void setArguments(String... args) {
        this.regex = args[0];
        this.replacement = args[1];
    }

}
