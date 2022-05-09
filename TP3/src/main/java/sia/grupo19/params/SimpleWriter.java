package sia.grupo19.params;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import sia.grupo19.params.SimpleParams.PrintHistoryMode;

public class SimpleWriter {
    public SimpleWriter(SimpleSolution solution, String path) {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd+hh-mm-ss-a").format(new Date());
        try {
            // File file = new File(path + timeStamp + ".log");
            FileWriter myWriter = new FileWriter(path + timeStamp + ".json");
            myWriter.write("{\"SolutionInfo\": ");
            myWriter.write(solution.toString());
            if (solution.getParams().getPrintHistory() != PrintHistoryMode.OFF) {
                if (solution.getParams().getPrintHistory() == PrintHistoryMode.ITERS
                        || solution.getParams().getPrintHistory() == PrintHistoryMode.BOTH) {
                    myWriter.write(",\"IterationsInfo\":");
                    myWriter.write(solution.getIterationsInfo().toString());
                }

                if (solution.getParams().getPrintHistory() == PrintHistoryMode.EPOCHS
                        || solution.getParams().getPrintHistory() == PrintHistoryMode.BOTH) {
                    myWriter.write(",\"EpochsInfo\":");
                    myWriter.write(solution.getEpochsInfo().toString());
                }

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
