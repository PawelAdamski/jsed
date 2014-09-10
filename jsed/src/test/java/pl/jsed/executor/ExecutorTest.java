package pl.jsed.executor;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import org.junit.Test;

import pl.jsed.processors.LineProcessor;
import pl.jsed.processors.Replace;

public class ExecutorTest {

    private LineProcessor processor;
    private Writer output = new StringWriter();
    private Reader input;

    Executor executor = new Executor();

    @Test
    public void singleLine() throws IOException {
        //GIVEN
        input = new StringReader("aabbcc");
        processor = new Replace();
        processor.setArguments("bb", "cc");

        //WHEN
        executor.run(input, output, processor);

        //THEN
        assertEquals("aacccc", output.toString());
    }

    @Test
    public void threeLines() throws IOException {
        //GIVEN
        input = new StringReader("aa\nab\nac");
        processor = new Replace();
        processor.setArguments("a", "1");

        //WHEN
        executor.run(input, output, processor);

        //THEN
        assertEquals("11\n1b\n1c", output.toString());
    }

    @Test
    public void emptyInput() throws IOException {
        //GIVEN
        input = new StringReader("");
        processor = new Replace();
        processor.setArguments("a", "1");

        //WHEN
        executor.run(input, output, processor);

        //THEN
        assertEquals("", output.toString());
    }

    @Test
    public void three_lines_with_one_empty() throws IOException {
        //GIVEN
        input = new StringReader("aa\n\nbb");
        processor = new Replace();
        processor.setArguments("a", "1");

        //WHEN
        executor.run(input, output, processor);

        //THEN
        assertEquals("11\n\nbb", output.toString());
    }

}
