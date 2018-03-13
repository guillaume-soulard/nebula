package com.nebula.core.output.stdout;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import java.io.PrintStream;

import static org.mockito.Mockito.*;

public class StandardOutputOutputTest {

    private PrintStream printStream;

    @Before
    public void setUp() {
        printStream = mock(PrintStream.class);
        System.setOut(printStream);
    }

    @Test
    public void write_should_write_test_in_stdout() {

        // GIVEN
        StandardOutputOutput output = new StandardOutputOutput();
        String formattedObject = "test";

        // WHEN
        output.write(formattedObject);

        // THEN
        verify(printStream, times(1)).print(formattedObject);
    }

    @Test
    public void write_should_flush_stdout_after_writing_object() {

        // GIVEN
        StandardOutputOutput output = new StandardOutputOutput();

        // WHEN
        output.write("test");

        // THEN
        InOrder inOrder = inOrder(printStream);
        inOrder.verify(printStream, times(1)).print(anyString());
        inOrder.verify(printStream, times(1)).flush();
    }
}
