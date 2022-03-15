package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import main.Solver.Solution;

public class Writer {

    public Writer(Solution solution, String path) {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd+hh-mm-ss").format(new Date());
        try {
            File file = new File(path + timeStamp + ".txt");
            FileWriter myWriter = new FileWriter(path + timeStamp + ".txt");
            myWriter.write(solution.toString());
            myWriter.write(solution.writeSolutionPath());
            myWriter.close();
            System.out.println("Successfully wrote to the file: " + path + timeStamp + ".txt");
        } catch (IOException e) {
            System.out.println("IOException ocurred");
            e.printStackTrace();
        }
    }
}
