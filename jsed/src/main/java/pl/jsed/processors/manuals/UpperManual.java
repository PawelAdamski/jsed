package pl.jsed.processors.manuals;

public class UpperManual implements LineProcessorManual {

    public String getManual() {
        StringBuilder ret = new StringBuilder();
        ret.append("Manual of executor \"upper\"\n");
        ret.append("Usage jsed upper [REGEX] [FILE]...\n");
        ret.append("Changes all occurrences of [REGEX] in all provided files to uppercase.");

        return ret.toString();
    }

    public String getName() {
        return "upper";
    }

}
