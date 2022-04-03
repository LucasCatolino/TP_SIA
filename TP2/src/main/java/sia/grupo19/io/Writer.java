package sia.grupo19.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Writer {

    public static class Written {
        @SerializedName("SolutionInfo")
        public Solution solution;

        @SerializedName("GenerationsInfo")
        public List<GenerationInfo> generationsInfo;

        public Written(Solution solution, List<GenerationInfo> generationInfo) {
            this.solution = solution;
            this.generationsInfo = generationInfo;
        }
    }

    public Writer(Solution solution, String path) {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd+hh-mm-ss").format(new Date());
        try {
            // File file = new File(path + timeStamp + ".log");
            FileWriter myWriter = new FileWriter(path + timeStamp + ".json");
            myWriter.write("{\"SolutionInfo\": ");
            myWriter.write(solution.toString());
            if (solution.getParams().isPrintHistory()) {
                myWriter.write(",\"GenerationsInfo\":");
                myWriter.write(solution.getGenerationsInfo().toString());

            }
            myWriter.write("}");
            myWriter.close();
            System.out.println("Successfully wrote to the file: " + path + timeStamp + ".json");
        } catch (IOException e) {
            System.out.println("IOException ocurred");
            e.printStackTrace();
        }
    }
}
