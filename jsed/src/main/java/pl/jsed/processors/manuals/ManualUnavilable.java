package pl.jsed.processors.manuals;

public class ManualUnavilable implements LineProcessorManual {

    public String getManual() {
        return "Manual is not available";
    }

    public String getName() {
        return "ManualUnavilable";
    }

}
