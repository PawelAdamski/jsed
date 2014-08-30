package pl.jsed.processors;

public abstract class LineProcessor {

    protected String line;

    protected String fileName;

    public abstract String process();

    public void setLine(String line) {
        this.line = line;

    }

    public abstract String getName();

    public abstract int getNumberOfArguments();

    public abstract void setArguments(String... args);
}
