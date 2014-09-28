package pl.jsed.processors;

import java.util.Optional;

public abstract class LineProcessor {

    protected String line;

    protected String fileName;

    public abstract Optional<String> process();

    public void setLine(String line) {
        this.line = line;

    }

    public abstract String getName();

    public abstract int getNumberOfArguments();

    public abstract void setArguments(String... args);

    public Optional<String> writeAfterFile() {
        return Optional.empty();
    }

    public Optional<String> writeBeforeFile() {
        return Optional.empty();
    }
}
