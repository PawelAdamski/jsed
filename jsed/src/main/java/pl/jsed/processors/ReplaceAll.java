package pl.jsed.processors;

public class ReplaceAll extends LineProcessor {

    private String regex;
    private String replacement;

    @Override
    public String process() {
        return line.replaceAll(regex, replacement);
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
