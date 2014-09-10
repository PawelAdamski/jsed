package pl.jsed;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class LineContaingTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    File inputFile;

    @Before
    public void setUp() throws IOException {
        inputFile = folder.newFile();
        String inputData = "abba\naabbaa\nbaaab";
        FileUtils.writeStringToFile(inputFile, inputData);
    }

    @Test
    public void lines_with_a() throws IOException {
        String[] args = new String[] { "linesContaining", "a", inputFile.getAbsolutePath() };
        App.main(args);
        assertEquals("abba\naabbaa\nbaaab", getResult(inputFile));
    }

    @Test
    public void lines_with_aabbaa() throws IOException {
        String[] args = new String[] { "linesContaining", "aabbaa", inputFile.getAbsolutePath() };
        App.main(args);
        assertEquals("aabbaa", getResult(inputFile));
    }

    @Test
    public void single_line_with_abba() throws IOException {
        String[] args = new String[] { "linesContaining", "\\Aabba\\z", inputFile.getAbsolutePath() };
        App.main(args);
        assertEquals("abba", getResult(inputFile));
    }

    @Test
    public void no_matching() throws IOException {
        String[] args = new String[] { "linesContaining", "xyz", inputFile.getAbsolutePath() };
        App.main(args);
        assertEquals("", getResult(inputFile));
    }

    private String getResult(File inputFile) throws IOException {
        return FileUtils.readFileToString(inputFile);
    }

}
