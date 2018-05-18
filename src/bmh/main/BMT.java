package bmh.main;

import java.util.*;

// this class handles the creation and comparison of the BMT
public class BMT {

    private String[][] myBMTTable = null;
    private String compare = null;

    /*
     * @param input     Takes in the word to create a BMT on. Best used at the start of the program due to complexity.
     */
    public void constructBMT(String input) {

        // this will be our movement variable, allow for re-using
        int i = 0;
        input = compare = input.toLowerCase();

        // first grab all unique characters, meaning no duplicates and return the length
        // using set caused miss ordering, linkedhashset fixes this
        LinkedHashSet<String> uniqueCharacters = new LinkedHashSet<String>(Arrays.asList(input.split("")));
        String[] processedArray = uniqueCharacters.toArray(new String[uniqueCharacters.size()]);

        // now allocate memory for BMT using our array and fill
        myBMTTable = new String[2][processedArray.length];
        for(i = 0; i < myBMTTable[0].length; i++) myBMTTable[0][i] = processedArray[i];

        // construct BMT, first a collection
        Map<Character, Integer> myMap = calculateOccurences(input);
        // let k move the through the processed array
        for(int k = 0; k < processedArray.length; k++) {
            // case, were the last character in input
            if(input.lastIndexOf(processedArray[k]) == input.length() - 1) {
                // we don't have any duplicates
                if(myMap.get(processedArray[k].charAt(0)) == 1) {
                    myBMTTable[1][k] = "" + input.length();
                } else
                    // we have a duplicate
                    if(myMap.get(processedArray[k].charAt(0)) > 1) {
                        int index = (input.length() - 1) - new StringBuffer(input).reverse().indexOf(processedArray[k], 1);
                        myBMTTable[1][k] = "" + (input.length() - 1 - index);
                } else
                        System.err.println("Error! " + processedArray[k] + " doesn't exist?");
            } else {
                // case were not at the end
                if(myMap.get(processedArray[k].charAt(0)) == 1) {
                    myBMTTable[1][k] = "" + (input.length() - 1 - input.lastIndexOf(processedArray[k]));
                } else
                    // we have a duplicate
                    if(myMap.get(processedArray[k].charAt(0)) > 1) {
                        int index = (input.length() - 1) - new StringBuffer(input).reverse().indexOf(processedArray[k], 1);
                        myBMTTable[1][k] = "" + (input.length() - 1 - index);
                    } else
                        System.err.println("Error! " + processedArray[k] + " doesn't exist?");
            }
        }

    }

    private Map<Character, Integer> calculateOccurences(String input) {
        Map<Character, Integer> charMap = new HashMap<Character, Integer>();
        char[] arr = input.toCharArray();

        for (char value: arr) {

            if (Character.isLetter(value)) {
                if (charMap.containsKey(value)) {
                    charMap.put(value, charMap.get(value) + 1);

                } else {
                    charMap.put(value, 1);
                }
            }
        }
        return charMap;
    }

    public int find(String input) {
        input = input.toLowerCase();
        if(myBMTTable == null) {
            throw new java.lang.Error("BMT is null, re run with constructed BMT");
        }
        int pos = compare.length();
        int current = 0;
        do {
            try {
                pos = compareStrings(input.substring(current, pos), compare);
                // does it contain? If so we'll use that numerical value
                List<String> tmpList = Arrays.asList(myBMTTable[0]);
                if (pos != -1) {
                    if (tmpList.contains("" + input.substring(current, (current + compare.length())).charAt(pos))) {
                        current += Integer.parseInt(myBMTTable[1][tmpList.indexOf("" + input.substring(current, (current + compare.length())).charAt(pos))]);
                        pos = compare.length() + current;
                    } else {
                        // regex, move length
                        current += compare.length();
                        pos = compare.length() + current;
                    }
                }
            } catch (Exception e) {
                return -1;
            }
        } while (pos > -1);
        return current;
    }

    /*
     * @param x is the input main
     * @param y is the sub string
     * @return Method returns -1 for found else the index of a nonmatching character.
     */
    private int compareStrings(String x, String y) {
        for (int start = y.length() - 1, i = start; i >= 0; i--) {
            if (y.charAt(i) != x.charAt(i)) {
                return i;
            }
        }
        return -1;
    }

}
