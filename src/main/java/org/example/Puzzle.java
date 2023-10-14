package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a puzzle which contains a table of characters
 * and a list of words. It is capable of parsing a chunk of data from a BufferedReader
 * and can find all occurrences of a specific character within the table.
 */
public class Puzzle {

    private final int nRows;
    private final int nColumns;
    private final String[][] data;
    private final List<String> words;

    /**
     * Constructor of Puzzle class which initializes its attributes.
     *
     * @param data   2D array of strings representing the character table
     * @param words  list of words
     */
    public Puzzle(String[][] data, List<String> words) {
        this.nRows = data.length;
        this.nColumns = data[0].length;
        this.data = data;
        this.words = words;
    }

    /**
     * Parses a chunk of data from a BufferedReader and constructs a Puzzle object.
     *
     * @param reader the BufferedReader from which to read the data
     * @return a Puzzle object or null if end of file is reached
     * @throws IOException if an I/O error occurs
     */
    public static Puzzle parseChunk(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        if (line == null) {
            return null;
        }

        String[] dimensions = line.split(" ");
        int nRows = Integer.parseInt(dimensions[0]);
        int nColumns = Integer.parseInt(dimensions[1]);

        String[][] data = new String[nRows][nColumns];

        for (int i = 0; i < nRows; i++) {
            line = reader.readLine();
            if (line != null) {
                data[i] = line.split(" ");
            }
        }

        line = reader.readLine();
        List<String> words = new ArrayList<>();
        if (line != null) {
            String[] wordsArray = line.split(" ");
            for (String word : wordsArray) {
                words.add(word);
            }
        }

        return new Puzzle(data, words);
    }

    /**
     * Finds all occurrences of a specific character within the data table
     * and returns their positions in a stack.
     *
     * @param character the character to be found
     * @return a stack containing the positions of the character
     */
    public ArrayStack<Position> findAllOccurrencesOfCharacter(String character) {
        ArrayStack<Position> stack = new ArrayStack<>();

        // Loop through each element in the data
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nColumns; j++) {
                // If the character matches, add its position to the stack
                if (data[i][j].equals(character)) {
                    stack.push(new Position(i, j));
                }
            }
        }

        return stack;
    }

    /**
     * Prints the character table of the puzzle.
     */
    public void printPuzzle() {
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nColumns; j++) {
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Provides the list of words associated with the puzzle.
     *
     * @return a list of words
     */
    public List<String> getWords() {
        return words;
    }
}
