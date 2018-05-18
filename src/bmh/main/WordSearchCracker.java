package bmh.main;

import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;

public class WordSearchCracker {

    private BMT myBMT;
    private LoadWordSearch lws;

    private String output = "output.cnf";
    private PrintWriter writer;

    private String[] myWords;
    private String[][] myWordSearch;

    public WordSearchCracker(File dataFile, File searchWords) {
        myBMT = new BMT();
        lws = new LoadWordSearch();
        try {
            writer = new PrintWriter(new File(output));
        } catch (Exception e) {
            e.printStackTrace();
        }
        myWordSearch = lws.readInWordSearch(dataFile);
        myWords = lws.readInSearchWords(searchWords);
    }

    public void crack() {
        /*
         * cases
         * 1. Row
         * 2. Column
         * 3 & 4 were found on stack overflow for an optimal solution
         * 3. Diagonal Left to Right https://stackoverflow.com/questions/36037764/diagonal-lines-in-nonsquare-array
         * 4. Diagonal Right to Left https://stackoverflow.com/questions/36037764/diagonal-lines-in-nonsquare-array
         */
        StringBuilder sb = new StringBuilder("");
        int k = 0;
        int r = 0;
        int c = 0;
        int row = 0;
        int col = 0;
        int pos = 0;
        boolean found = false;
        while(k < myWords.length) {
            found = false;
            // construct bmt for search word
            myBMT.constructBMT(myWords[k]);
            // case 1
            for (row = 0; row < myWordSearch.length; row++) {
                if((pos = myBMT.find(Arrays.toString(myWordSearch[row]).replaceAll(",\\s", "").replace("[", "").replace("]", ""))) != -1) {
                    writer.println("Found Word : " + myWords[k] + ". Row : " + row + ", Position : " + pos);
                    found = true;
                    k++;
                    break;
                }
                sb = new StringBuilder(Arrays.toString(myWordSearch[row]).replaceAll(",\\s", "").replace("[", "").replace("]", ""));
                if((pos = myBMT.find(sb.reverse().toString())) != -1) {
                    writer.println("Found Word (Reversed) : " + myWords[k] + ". Row : " + row + ", Position : " + pos);
                    found = true;
                    k++;
                    break;
                }
            }
            if(found) continue;

            // case 2 - was going to use Apache commons later
            for(col = 0; col < myWordSearch[0].length; col++) {
                sb = new StringBuilder("");
                // preprocess - Apache allows for column collection via a double, not a string return =[
                for(row = 0; row < myWordSearch.length; row++) sb.append(myWordSearch[row][col]);
                if((pos = myBMT.find(sb.toString())) != -1) {
                    writer.println("Found Word : " + myWords[k] + ". Column : " + col + ", Position : " + pos);
                    found = true;
                    k++;
                    break;
                }
                sb.reverse();
                if((pos = myBMT.find(sb.toString())) != -1) {
                    writer.println("Found Word (Reversed) : " + myWords[k] + ". Column : " + col + ", Position : " + pos);
                    found = true;
                    k++;
                    break;
                }
            }
            if(found) continue;

            // case 3
            r = myWordSearch.length;
            c = myWordSearch[0].length;
            for(int i = 0; i < c; i++) {
                sb = new StringBuilder("");
                col = i;
                row = 0;
                while(col >= 0 && row < r) {
                    sb.append(myWordSearch[row][col]);
                    row++;
                    col--;
                }
                if((pos = myBMT.find(sb.toString())) != -1) {
                    writer.println("Found Word : " + myWords[k] + ". Diagonally : [" + (row - myWords[k].length()) + "," + (col - myWords[k].length()) + "], Position : " + pos);
                    found = true;
                    k++;
                    break;
                }
                sb.reverse();
                if((pos = myBMT.find(sb.toString())) != -1) {
                    writer.println("Found Word (Reversed) : " + myWords[k] + ". Diagonally : [" + (row - myWords[k].length()) + "," + (col - myWords[k].length()) + "], Position : " + pos);
                    found = true;
                    k++;
                    break;
                }

            }
            if(found) continue;

            for(int i = 0; i < r; i++) {
                sb = new StringBuilder("");
                row = i;
                col = c - 1;
                while(row < r) {
                    sb.append(myWordSearch[row][col]);
                    row++;
                    col--;
                }
                if((pos = myBMT.find(sb.toString())) != -1) {
                    writer.println("Found Word : " + myWords[k] + ". Diagonally : [" + (row - myWords[k].length()) + "," + (col - myWords[k].length()) + "], Position : " + pos);
                    found = true;
                    k++;
                    break;
                }
                sb.reverse();
                if((pos = myBMT.find(sb.toString())) != -1) {
                    writer.println("Found Word (Reversed) : " + myWords[k] + ". Diagonally : [" + (row - myWords[k].length()) + "," + (col - myWords[k].length()) + "], Position : " + pos);
                    found = true;
                    k++;
                    break;
                }
            }
            if(found) continue;

            // case 4
            r = myWordSearch.length;
            c = myWordSearch[0].length;
            for(int i = c - 1; i > 0; i--) {
                sb = new StringBuilder("");
                col = i;
                row = 0;
                while(col < c) {
                    sb.append(myWordSearch[row][col]);
                    row++;
                    col++;
                }
                if((pos = myBMT.find(sb.toString())) != -1) {
                    writer.println("Found Word : " + myWords[k] + ". Diagonally : [" + (row - myWords[k].length()) + "," + (col - myWords[k].length()) + "], Position : " + pos);
                    found = true;
                    k++;
                    break;
                }
                sb.reverse();
                if((pos = myBMT.find(sb.toString())) != -1) {
                    writer.println("Found Word (Reversed) : " + myWords[k] + ". Diagonally : [" + (row - myWords[k].length()) + "," + (col - myWords[k].length()) + "], Position : " + pos);
                    found = true;
                    k++;
                    break;
                }
            }
            if(found) continue;

            for(int i = 0; i < r; i++) {
                sb = new StringBuilder("");
                row = i; col = 0;
                while(row < r) {
                    sb.append(myWordSearch[row][col]);
                    row++;
                    col++;
                }
                if((pos = myBMT.find(sb.toString())) != -1) {
                    writer.println("Found Word : " + myWords[k] + ". Diagonally : [" + (row - myWords[k].length()) + "," + (col - myWords[k].length()) + "], Position : " + pos);
                    found = true;
                    k++;
                    break;
                }
                sb.reverse();
                if((pos = myBMT.find(sb.toString())) != -1) {
                    writer.println("Found Word (Reversed) : " + myWords[k] + ". Diagonally : [" + (row - myWords[k].length()) + "," + (col - myWords[k].length()) + "], Position : " + pos);
                    found = true;
                    k++;
                    break;
                }
            }
            if(found) continue;

            k++;
        }
        writer.flush();
        writer.close();
    }

    public static void main(String[] args) {
        if(args.length != 2) {
            System.err.println("Required (2) arguments.\nArgument 1 - Wordsearch.cnf\nArgument 2 - Search Words.txt");
            System.exit(1);
        }
        new WordSearchCracker(new File(args[0]), new File(args[1])).crack();
    }

}
