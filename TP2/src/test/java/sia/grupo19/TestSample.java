package sia.grupo19;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import sia.grupo19.Utils.Sample;

public class TestSample {
    double[] xi = { 4.4793, -4.0765, -4.0765 };

    @Test
    public void test01() {
        Sample s = new Sample(4.4793, -4.0765, -4.0765, 0);

        assertTrue(Arrays.equals(s.getXi(), xi));
        assertEquals(s.getZeta(), 0);
    }

    @Test
    public void test02() {
        Sample s = new Sample(xi, 0);

        assertTrue(Arrays.equals(s.getXi(), xi));
        assertEquals(s.getZeta(), 0);
    }
}
