package kmfsample;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class ExpectedResultTest {

    StringBuffer buffer = new StringBuffer();

    @Test
    public void testOutput() {

        PrintStream out = System.out;
        PrintStream err = System.err;

        System.setOut(new PrintStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                buffer.append((char) b);
            }
        }));
        System.setErr(new PrintStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                buffer.append(b);
            }
        }));
        App.main(null);

        //Wait all callback end
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.setOut(out);
        System.setErr(err);

        String result = buffer.toString();
        System.out.println(result);


        Assert.assertEquals("test2\n" +
                "====End training === \n" +
                "-20.0 , 0.0 => SUSPICIOUS\n" +
                "-20.0 , 0.5 => SUSPICIOUS\n" +
                "-20.0 , 0.6 => SUSPICIOUS\n" +
                "-20.0 , 1.0 => SUSPICIOUS\n" +
                "10.0 , 0.0 => SUSPICIOUS\n" +
                "10.0 , 0.5 => CORRECT\n" +
                "10.0 , 0.6 => CORRECT\n" +
                "10.0 , 1.0 => SUSPICIOUS\n" +
                "30.0 , 0.0 => SUSPICIOUS\n" +
                "30.0 , 0.5 => CORRECT\n" +
                "30.0 , 0.6 => CORRECT\n" +
                "30.0 , 1.0 => SUSPICIOUS\n" +
                "100.0 , 0.0 => SUSPICIOUS\n" +
                "100.0 , 0.5 => SUSPICIOUS\n" +
                "100.0 , 0.6 => SUSPICIOUS\n" +
                "100.0 , 1.0 => SUSPICIOUS\n", result);

    }

}
