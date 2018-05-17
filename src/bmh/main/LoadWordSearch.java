package bmh.main;

import java.io.File;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoadWordSearch {

    public String[][] readIn(File dataFile) {
        String myMatrix[][] = null;
        try {
            // the datafile takes to cnf format
            Pattern p = Pattern.compile("c");
            Scanner scan = new Scanner(dataFile);
            while(scan.hasNext(p));
            myMatrix = new String[scan.nextInt()][scan.nextInt()];
            int row = 0;
            int col = 0;
            while(scan.hasNext()) {
                myMatrix[row][col] = scan.next();
                col++;
                if(col % myMatrix[row].length == 0 && col != 0) {
                    row++;
                    col = 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myMatrix;
    }

}
