package pl.jsed.processors;

public class Replace extends LineProcessor {

    private String target;
    private String replacement;

    public Replace(String target, String replacement) {
        this.target = target;
        this.replacement = replacement;
    }

    @Override
    public String process() {
        return line.replace(target, replacement);
    }

}
