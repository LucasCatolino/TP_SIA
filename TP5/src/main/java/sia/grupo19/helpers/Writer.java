package sia.grupo19.helpers;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;

import com.google.gson.Gson;

public class Writer {
    public Writer(Object o, String path) {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd+hh-mm-ss-a").format(new Date());

        try {
            FileWriter myParserWriter = new FileWriter(path + "-PARAMS-" + timeStamp + ".out");
            myParserWriter.write(new Gson().toJson(o));
            myParserWriter.close();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

}
