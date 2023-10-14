//package org.example;
//
//import java.util.List;
//
///**
// * The DataChunk class represents a portion (or "chunk") of the data extracted from a text file.
// * It contains the table data in a 2D array format and a list of words.
// */
//public class DataChunk {
//    private final ArrayList<ArrayList<String>> tableData;  // 2D ArrayList storing the table data
//    private final List<String> wordData; // List storing the words
//
//    /**
//     * Constructor initializes the tableData and wordData with given input.
//     *
//     * @param tableData 2D array of strings representing the table.
//     * @param wordData  List of strings representing the words.
//     */
//    public DataChunk(ArrayList<ArrayList<String>> tableData, List<String> wordData) {
//        this.tableData = tableData;
//        this.wordData = wordData;
//    }
//
//    /**
//     * Get the table data from the data chunk.
//     *
//     * @return A 2D array of strings representing the table.
//     */
//    public ArrayList<ArrayList<String>> getTableData() {
//        return tableData;
//    }
//
//    /**
//     * Get the words data from the data chunk.
//     *
//     * @return A list of strings representing the words.
//     */
//    public List<String> getWordData() {
//        return wordData;
//    }
//
//    /**
//     * Print the table data and word data in a readable format.
//     * This may be used for debugging or verification of data integrity.
//     */
//    public void print() {
//        // Loop through each row of the table data
//        for (String[] row : tableData) {
//            // Loop through each element in the row
//            for (String element : row) {
//                // Print the element followed by a space
//                System.out.print(element + " ");
//            }
//            // Move to the next line after printing all elements in a row
//            System.out.println();
//        }
//
//        // Print a newline to separate the table data and word data
//        System.out.println();
//
//        // Loop through each word in the word data and print it
//        for (String word : wordData) {
//            System.out.print(word + " ");
//        }
//
//        // Move to the next line after printing all words
//        System.out.println();
//    }
//}
