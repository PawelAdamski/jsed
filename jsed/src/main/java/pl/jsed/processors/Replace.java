package pl.jsed.processors;

public class Replace extends LineProcessor {

    private String target;
    private String replacement;

    @Override
    public String process() {
        return line.replace(target, replacement);
    }

    @Override
    public String getName() {
        return "replace";
    }

    @Override
    public int getNumberOfArguments() {
        return 2;
    }

    @Override
    public void setArguments(String... args) {
        this.target = args[0];
        this.replacement = args[1];
    }

}
