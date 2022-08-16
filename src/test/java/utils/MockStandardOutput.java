package utils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MockStandardOutput {

    private ByteArrayOutputStream mockStdOut = new ByteArrayOutputStream();
    private PrintStream originalStdOut;

    public void capture() {
        mockStdOut = new ByteArrayOutputStream();
        originalStdOut = System.out;
        System.setOut(new PrintStream(mockStdOut));
    }

    public void reset() {
        System.setOut(originalStdOut);
    }

    public String read() {
        return mockStdOut.toString();
    }

}
