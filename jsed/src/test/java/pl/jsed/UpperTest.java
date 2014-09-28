package pl.jsed;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class UpperTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    File oneLineFile;

    @Before
    public void setUp() throws IOException {
        oneLineFile = folder.newFile();
        String inputData = "aababccacca";
        FileUtils.writeStringToFile(oneLineFile, inputData);
    }

    @Test
    public void upper_a_in_one_line() throws IOException {
        String[] args = new String[] { "upper", "a", oneLineFile.getAbsolutePath() };
        JSed.main(args);
        assertEquals("AAbAbccAccA", getResult(oneLineFile));
    }

    @Test
    public void upper_aba_in_one_line() throws IOException {
        String[] args = new String[] { "upper", "a.a", oneLineFile.getAbsolutePath() };
        JSed.main(args);
        assertEquals("aABAbccacca", getResult(oneLineFile));
    }

    @Test
    public void upper_overlapping_matches_in_one_line() throws IOException {
        String[] args = new String[] { "upper", ".b.", oneLineFile.getAbsolutePath() };
        JSed.main(args);
        assertEquals("aABABCcacca", getResult(oneLineFile));
    }

    @Test
    public void upper_two_dots_in_one_line() throws IOException {
        String[] args = new String[] { "upper", "..", oneLineFile.getAbsolutePath() };
        JSed.main(args);
        assertEquals("AABABCCACCA", getResult(oneLineFile));
    }

    @Test
    public void no_matching() throws IOException {
        String[] args = new String[] { "upper", "xyz", oneLineFile.getAbsolutePath() };
        JSed.main(args);
        assertEquals("aababccacca", getResult(oneLineFile));
    }

    private String getResult(File inputFile) throws IOException {
        return FileUtils.readFileToString(inputFile);
    }

}
