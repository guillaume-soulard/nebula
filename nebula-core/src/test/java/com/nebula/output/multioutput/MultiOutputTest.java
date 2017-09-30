package com.nebula.output.multioutput;

import com.nebula.output.Output;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MultiOutputTest {

    @Test
    public void write_should_write_in_one_output() throws Exception {

        // GIVEN
        Output output = mock(Output.class);
        MultiOutput multiOutput = new MultiOutput(output);
        String formattedObject = "formatted object";

        // WHEN
        multiOutput.write(formattedObject);

        // THEN
        verify(output, times(1)).write(formattedObject);
    }

    @Test
    public void write_should_write_in_two_outputs() throws Exception {

        // GIVEN
        Output output1 = mock(Output.class);
        Output output2 = mock(Output.class);
        MultiOutput multiOutput = new MultiOutput(output1, output2);
        String formattedObject = "formatted object";

        // WHEN
        multiOutput.write(formattedObject);

        // THEN
        verify(output1, times(1)).write(formattedObject);
        verify(output2, times(1)).write(formattedObject);
    }
}
