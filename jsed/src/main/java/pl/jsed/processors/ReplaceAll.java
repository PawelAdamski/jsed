package pl.jsed.processors;

public class ReplaceAll extends LineProcessor {

    private String regex;
    private String replacement;

    public ReplaceAll(String regex, String replacement) {
        this.regex = regex;
        this.replacement = replacement;
    }

    @Override
    public String process() {
        return line.replaceAll(regex, replacement);
    }

}
