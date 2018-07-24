package com.nebula.core.output.stdout;

import com.nebula.core.output.OutputParameter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import java.io.PrintStream;

import static org.mockito.Mockito.*;

class StandardOutputOutputTest {

    private PrintStream printStream;

    @BeforeEach
    void setUp() {
        printStream = mock(PrintStream.class);
        System.setOut(printStream);
    }

    @Test
    void write_should_write_test_in_stdout() {

        // GIVEN
        StandardOutputOutput output = new StandardOutputOutput();
        String formattedObject = "test";

        // WHEN
        output.write(new OutputParameter(formattedObject, null));

        // THEN
        verify(printStream, times(1)).print(formattedObject);
    }

    @Test
    void write_should_flush_stdout_after_writing_object() {

        // GIVEN
        StandardOutputOutput output = new StandardOutputOutput();

        // WHEN
        output.write(new OutputParameter("test", null));

        // THEN
        InOrder inOrder = inOrder(printStream);
        inOrder.verify(printStream, times(1)).print(anyString());
        inOrder.verify(printStream, times(1)).flush();
    }
}
