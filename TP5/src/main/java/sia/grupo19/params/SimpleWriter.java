package sia.grupo19.params;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;

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
                    /*
                     * myWriter.write(",\"IterationsInfo\":");
                     * myWriter.write(solution.getIterationsInfo().toString());
                     */
                    FileWriter myTrainWriter = new FileWriter(path + "-ERROR-" + timeStamp + ".txt");
                    for (int i = 0; i < solution.getIterationsInfo().size(); i++) {
                        myTrainWriter.write(i + "," + solution.getIterationsInfo().get(i).getError() + "\n");
                    }
                    myTrainWriter.close();
                    System.out.println("Successfully wrote to the file: " + path + "-ERROR-" + timeStamp + ".txt");
                }

                if (solution.getParams().getPrintHistory() == PrintHistoryMode.EPOCHS
                        || solution.getParams().getPrintHistory() == PrintHistoryMode.BOTH) {
                    /*
                     * myWriter.write(",\"EpochsInfo\":");
                     * myWriter.write(solution.getEpochsInfo().toString());
                     */
                    FileWriter myTrainWriter = new FileWriter(path + "-TRAIN-ACC-" + timeStamp + ".txt");
                    for (int i = 0; i < solution.getEpochsInfo().size(); i++) {
                        myTrainWriter.write(i + "," + solution.getEpochsInfo().get(i).getTrainAccuracy() + "\n");
                    }
                    myTrainWriter.close();
                    System.out.println("Successfully wrote to the file: " + path + "-TRAIN-ACC-" + timeStamp + ".txt");

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

    public SimpleWriter(List<SimpleSolution> solutions, String path) {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd+hh-mm-ss-a").format(new Date());
        int sol = 0;

        try {
            FileWriter myParserWriter = new FileWriter(path + "-PARAMS-" + timeStamp + ".txt");
            myParserWriter.write(new Gson().toJson(solutions.get(0).getParams()));
            myParserWriter.close();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        for (SimpleSolution solution : solutions) {
            try {
                // File file = new File(path + timeStamp + ".log");
                FileWriter myTrainWriter = new FileWriter(path + "-" + sol + "-TRAIN-ACC-" + timeStamp + ".txt");
                for (int i = 0; i < solution.getEpochsInfo().size(); i++) {
                    myTrainWriter.write(i + "," + solution.getEpochsInfo().get(i).getTrainAccuracy() + "\n");
                }
                myTrainWriter.close();
                System.out.println(
                        "Successfully wrote to the file: " + path + "-" + sol + "-TRAIN-ACC-" + timeStamp + ".txt");

                FileWriter myTestWriter = new FileWriter(path + "-" + sol + "-TEST-ACC-" + timeStamp + ".txt");
                for (int i = 0; i < solution.getEpochsInfo().size(); i++) {
                    myTestWriter.write(i + "," + solution.getEpochsInfo().get(i).getTestAccuracy() + "\n");
                }
                myTestWriter.close();
                System.out.println(
                        "Successfully wrote to the file: " + path + "-" + sol + "-TEST-ACC-" + timeStamp + ".txt");

                sol++;

            } catch (IOException e) {
                System.out.println("IOException ocurred");
                e.printStackTrace();
            }

        }

    }
}
