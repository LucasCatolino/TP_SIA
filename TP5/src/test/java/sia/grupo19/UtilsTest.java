package sia.grupo19;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import sia.grupo19.helpers.Utils;

public class UtilsTest {
	
	@Test
    public void testParseParams() throws Exception {
        double[][] latent= Utils.getTestingLatent();
        
        assertEquals(-1, latent[20][1], 0.01);
        assertEquals(0.29, latent[282][1], 0.01);
    }
}
