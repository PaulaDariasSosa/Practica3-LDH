package top;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import es.ull.esit.utilities.ExpositoUtilities;

/**
 * @brief Class to read a TOPTW problem from a file.
 */
public class TOPTWReader {

    /**
     * @brief Read a TOPTW problem from a file.
     * @param filePath Path to the file.
     * @return TOPTW problem.
     */
    public static TOPTW readProblem(String filePath) {
        TOPTW problem = null;
        BufferedReader reader = null;
        try {
            File instaceFile = new File(filePath);
            reader = new BufferedReader(new FileReader(instaceFile));
            String line = reader.readLine();
            line = ExpositoUtilities.simplifyString(line);
            String[] parts =line.split(" ");
            problem = new TOPTW(Integer.parseInt(parts[2]), Integer.parseInt(parts[1]));
            line = reader.readLine();
            line = null; parts = null;
            for (int i = 0; i < problem.getPOIs()+1; i++) {
                line = reader.readLine();
                line = ExpositoUtilities.simplifyString(line);
                parts = line.split(" ");
                problem.setX(i, Double.parseDouble(parts[1]));
                problem.setY(i, Double.parseDouble(parts[2]));
                problem.setServiceTime(i, Double.parseDouble(parts[3]));
                problem.setScore(i, Double.parseDouble(parts[4]));
                if(i==0) {
                    problem.setReadyTime(i, Double.parseDouble(parts[7]));
                    problem.setDueTime(i, Double.parseDouble(parts[8]));
                }
                else {
                    problem.setReadyTime(i, Double.parseDouble(parts[8]));
                    problem.setDueTime(i, Double.parseDouble(parts[9]));                    
                }
                line = null; parts = null;
            }
            problem.calculateDistanceMatrix();
        } catch (IOException e) {
            System.err.println(e);
            System.exit(0);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ex) {
                    System.err.println(ex);
                    System.exit(0);
                }
            }
        }
        problem.setMaxTimePerRoute(problem.getDueTime(0));
        return problem;
    }
    
}
