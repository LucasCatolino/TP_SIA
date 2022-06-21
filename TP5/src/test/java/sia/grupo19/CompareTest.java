package sia.grupo19;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import org.junit.Test;

import sia.grupo19.helpers.EpochInfo;
import sia.grupo19.helpers.ParamsParser;
import sia.grupo19.helpers.Utils;
import sia.grupo19.helpers.Writer;
import sia.grupo19.params.SimpleParser;
import sia.grupo19.params.SimpleSolution;
import sia.grupo19.params.SimpleWriter;

/**
 * Unit test for simple App.
 */
public class CompareTest {

    @Test
    public void Ex1aConf17Compare() throws Exception {
        ParamsParser parser = new ParamsParser("./resources/1aconfs/conf17.json", true);
        MultiLayerPerceptron mlp = new MultiLayerPerceptron(parser.getParams());
        mlp.run3_3();
        System.out
                .println(new Gson().toJson(parser.getParams().getHiddenLayersSizes()) + " - Error = " + mlp.getError());
    }

    @Test
    public void Ex1aConf9Compare() throws Exception {
        ParamsParser parser = new ParamsParser("./resources/1aconfs/conf9.json", true);
        MultiLayerPerceptron mlp = new MultiLayerPerceptron(parser.getParams());
        mlp.run3_3();
        System.out
                .println(new Gson().toJson(parser.getParams().getHiddenLayersSizes()) + " - Error = " + mlp.getError());
    }

    @Test
    public void Ex1aConf5Compare() throws Exception {
        ParamsParser parser = new ParamsParser("./resources/1aconfs/conf5.json", true);
        MultiLayerPerceptron mlp = new MultiLayerPerceptron(parser.getParams());
        mlp.run3_3();
        System.out
                .println(new Gson().toJson(parser.getParams().getHiddenLayersSizes()) + " - Error = " + mlp.getError());
    }

    @Test
    public void Ex1aConf3Compare() throws Exception {
        ParamsParser parser = new ParamsParser("./resources/1aconfs/conf3.json", true);
        MultiLayerPerceptron mlp = new MultiLayerPerceptron(parser.getParams());
        mlp.run3_3();
        System.out
                .println(new Gson().toJson(parser.getParams().getHiddenLayersSizes()) + " - Error = " + mlp.getError());
    }

    @Test
    public void Ex1aConf1Compare() throws Exception {
        ParamsParser parser = new ParamsParser("./resources/1aconfs/conf1.json", true);
        MultiLayerPerceptron mlp = new MultiLayerPerceptron(parser.getParams());
        mlp.run3_3();
        System.out
                .println(new Gson().toJson(parser.getParams().getHiddenLayersSizes()) + " - Error = " + mlp.getError());
    }

}
