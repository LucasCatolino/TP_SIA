package test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Test;

import main.Config;
import main.Parser;
import main.Config.Heuristics;
import main.Config.SearchMethods;
import main.Config.StrategyTypes;

public class TestsParser {
    Parser myParser = new Parser("/test/resources/config.txt");

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test01ReadsStuff() {
        Config myConfig = myParser.getConfig();
        assertEquals("123456708", myConfig.getPuzzle());
        assertEquals(StrategyTypes.UNINFORMED, myConfig.getStrategy());
        assertEquals(SearchMethods.BPA, myConfig.getMethod());
        assertEquals(1, myConfig.getLimit());
        assertEquals(Heuristics.INVALID, myConfig.getHSelected());
    }

}
