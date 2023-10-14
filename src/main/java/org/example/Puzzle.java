package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Puzzle {
    private int nRows;
    private int nColumns;
    private String[][] data;
    private List<String> words;

    // Create a Puzzle instance from provided data and words
    public Puzzle(int nRows, int nColumns, String[][] data, List<String> words) {
        // ... (Same validation and initialization as in the original Puzzle class)
        this.nRows = nRows;
        this.nColumns = nColumns;
        this.data = data;
        this.words = words;
    }

    // Parses a Puzzle instance from a BufferedReader
    public static Puzzle parsePuzzle(BufferedReader reader) throws IOException {
        String[] dimensions = reader.readLine().split(" ");
        int nRows = Integer.parseInt(dimensions[0]);
        int nColumns = Integer.parseInt(dimensions[1]);

        String[][] data = new String[nRows][nColumns];
        for (int i = 0; i < nRows; i++) {
            data[i] = reader.readLine().split(" ");
        }

        List<String> words = Arrays.asList(reader.readLine().split(" "));

        return new Puzzle(nRows, nColumns, data, words);
    }

    public int getNumberOfRows() {
        return nRows;
    }

    public int getNumberOfColumns() {
        return nColumns;
    }

    public String getElement(int row, int column) {
        // ... (Same validation and element retrieval as in the original Puzzle class)
        if (row < 0 || row >= nRows || column < 0 || column >= nColumns) {
            throw new IllegalArgumentException("Invalid row or column index");
        }
        return data[row][column];
    }

    public List<String> getWords() {
        return words;
    }
}
