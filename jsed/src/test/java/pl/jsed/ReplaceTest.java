package pl.jsed;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class ReplaceTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    File oneLineFile;

    @Before
    public void setUp() throws IOException {
        oneLineFile = folder.newFile();
        String inputData = "aabbcc";
        FileUtils.writeStringToFile(oneLineFile, inputData);
    }

    @Test
    public void replace_a_to_b_in_one_line() throws IOException {
        String[] args = new String[] { "replace", "a", "b", oneLineFile.getAbsolutePath() };
        App.main(args);
        assertEquals("bbbbcc", getResult(oneLineFile));
    }

    private String getResult(File inputFile) throws IOException {
        return FileUtils.readFileToString(inputFile);
    }

}
