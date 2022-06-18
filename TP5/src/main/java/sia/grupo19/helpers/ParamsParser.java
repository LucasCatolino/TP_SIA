package sia.grupo19.helpers;

import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;

public class ParamsParser {

    private ParamsContainer params;

    public ParamsParser(String path, boolean prod) throws IOException {
        params = new Gson().fromJson(new FileReader(path), ParamsContainer.class);
    }

    public ParamsContainer getParams() {
        return this.params;
    }

}
