package bmh.main;

import java.io.File;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoadWordSearch {

    /*
     * @param dataFile   Datafile should be in the format, n comments followed by n m rows and columns and lastly the word search
     */
    public String[][] readInWordSearch(File dataFile) {
        String[][] myMatrix = null;
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

    /*
     * @param dateFile  Datefile should be in the format of cnf, n comments followed by how many words following that, those number of words.
     */
    public String[] readInSearchWords(File dataFile) {
        String[] myWords = null;
        try {
            // the datafile takes to cnf format
            Pattern p = Pattern.compile("c");
            Scanner scan = new Scanner(dataFile);
            while(scan.hasNext(p));
            myWords = new String[scan.nextInt()];
            int count = 0;
            scan.nextLine();
            while(scan.hasNext()) {
               myWords[count] = scan.nextLine();
               count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myWords;
    }

}
