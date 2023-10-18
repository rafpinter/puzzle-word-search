package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
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

    // GETTERS
    /**
     * Provides the list of words associated with the puzzle.
     *
     * @return a list of words
     */
    public List<String> getWords() {
        return words;
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
                Position currentPosition = new Position(i, j);
                // Utilize the isCharacterAtPosition method to check character equality
                if (isCharacterAtPosition(currentPosition, character)) {
                    stack.push(currentPosition);
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
     * This method checks if the character at the specified position within
     * the puzzle's grid matches the provided character.
     *
     * @param position the position to check the character at
     * @param character the character to check equality against
     * @return true if characters match, false otherwise
     */
    public boolean isCharacterAtPosition(Position position, String character) {
        // Validate the position to ensure it's within the grid boundaries
        if (position.getRow() >= 0 && position.getRow() < nRows &&
                position.getColumn() >= 0 && position.getColumn() < nColumns) {
            // Check and return if the character at the position matches the provided character
            return data[position.getRow()][position.getColumn()].equals(character);
        }
        // If position is out of grid boundaries, return false
        return false;
    }


    /**
     * Finds and returns a stack of valid neighboring positions, based on a starting position and a search character.
     *
     * @param pos       the starting position to find neighbors around
     * @param character the character to search for in neighboring positions
     * @return a stack containing all valid neighboring positions containing the search character
     */
    public ArrayStack<Position> findValidNeighbors(Position pos, String character) {
        // Create a stack to hold valid neighbor positions.
        ArrayStack<Position> stack = new ArrayStack<>();

        // Define relative positions of all possible neighbors (self, left, right, up, down, and 4 diagonals).
        // Each array represents the [rowOffset, columnOffset] relative to the original position.
        int[][] neighbors = {
                {0, 0}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
        };

        // Loop over each possible relative neighbor.
        for(int[] n : neighbors) {
            // Calculate the absolute position of the neighbor by adding the offsets to the original position.
            int newRow = pos.getRow() + n[0];
            int newCol = pos.getColumn() + n[1];

            // Validate if the neighbor is within the puzzle boundaries.
            // - Row should be >= 0 and < total number of rows (nRows).
            // - Column should be >= 0 and < total number of columns (nColumns).
            if(newRow >= 0 && newRow < nRows && newCol >= 0 && newCol < nColumns) {
                // Check if the neighbor contains the desired character.
                // If true, add the neighbor's position to the stack.
                if(isCharacterAtPosition(new Position(newRow, newCol), character)) {
                    stack.push(new Position(newRow, newCol));
                }
            }
        }

        // Return the stack containing all valid neighbors.
        return stack;
    }


    /**
     * Retrieves the string at a specified position in the puzzle.
     *
     * @param pos A Position object containing the target row and column indices.
     * @return The string found at the specified position in the puzzle.
     */
    public String getStringAtPosition(Position pos) {
        // Ensure the position is within the bounds of the puzzle.
        if (pos.getRow() >= 0 && pos.getRow() < nRows && pos.getColumn() >= 0 && pos.getColumn() < nColumns) {
            // Return the string at the specified position.
            return data[pos.getRow()][pos.getColumn()];
        } else {
            // If the position is out of bounds, return null or handle accordingly.
            return null;
        }
    }


    /**
     * Prints the word followed by a sequence of positions in the format:
     * word: (row1,col1)->(row2,col2)->...->(rowN,colN)
     *
     * @param positionsDeque The deque containing the sequence of positions.
     */
    public static void printDequeOfPositions(String word, ArrayDeque<Position> positionsDeque) {
        // Using StringBuilder for efficient string concatenation.
        StringBuilder output = new StringBuilder(word + ": ");

        // Initialize iterator for positionsDeque to sequentially access its items.
        Iterator<Position> iterator = positionsDeque.iterator();

        // Loop through the positions in the deque.
        while (iterator.hasNext()) {
            // Append the string representation of the next position.
            output.append(iterator.next().getPositionString());

            // If there's another position after the current one, append an arrow.
            if (iterator.hasNext()) {
                output.append("->");
            }
        }

        // Print the concatenated string.
        System.out.println(output.toString());
    }



    /**
     * Prints a sequence of positions in the format:
     * (row1,col1)->(row2,col2)->...->(rowN,colN)
     *
     * @param positionsStack The stack containing the sequence of positions.
     */
    public static void printStackOfPositions(ArrayStack<Position> positionsStack) {
        // Using StringBuilder for efficient string concatenation.
        StringBuilder output = new StringBuilder();

        // Creating a copy of the original stack to avoid modifying the original.
        ArrayStack<Position> tempStack = new ArrayStack<>(positionsStack.size());
        ArrayStack<Position> reversedStack = new ArrayStack<>(positionsStack.size());

        while (!positionsStack.isEmpty()) {
            tempStack.push(positionsStack.pop());
        }

        // Reverse the stack, so we can print in the original order.
        while (!tempStack.isEmpty()) {
            reversedStack.push(tempStack.pop());
        }

        // Loop through the positions in the reversed stack.
        while (!reversedStack.isEmpty()) {
            Position position = reversedStack.pop();

            // Restore the original stack.
            positionsStack.push(position);

            // Append the string representation of the current position.
            output.append(position.getPositionString());

            // If there are more positions, append an arrow.
            if (!reversedStack.isEmpty()) {
                output.append(", ");
            }
        }

        // Print the concatenated string.
        System.out.println(output.toString());
    }


    /**
     * Validates if the characters at the given positions form the specified word.
     * @param positionsDeque An ArrayDeque of positions in the puzzle.
     * @param word The word to validate against.
     * @return True if the characters at the positions form the target word; false otherwise.
     */
    public boolean validateWord(ArrayDeque<Position> positionsDeque, String word) {
        StringBuilder concatQueue = new StringBuilder();

        ArrayDeque<Position> positionsDequeCopy = new ArrayDeque<>();
        positionsDequeCopy = positionsDeque.clone();

        // Create a temporary stack to retain the original order of positionsStack
        ArrayStack<Position> tempStack = new ArrayStack<>();

        // Pop each position from the stack, get the corresponding character from the puzzle and build the string
        while (!positionsDeque.isEmpty()) {
            Position pos = positionsDeque.removeFirst();
            String ch = data[pos.getRow()][pos.getColumn()];
            concatQueue.append(ch);

            // Push to temp stack for restoring order later
            tempStack.push(pos);
        }

        // Restore the order in positionsStack
        while (!tempStack.isEmpty()) {
            positionsDeque.addFirst(tempStack.pop());
        }

        if (concatQueue.toString().equals(word)) {
            printDequeOfPositions(word, positionsDequeCopy);
        }

        // Compare the constructed string with the word
//        System.out.println("Validating: " + concatQueue.toString() + " at " + i);
        return concatQueue.toString().equals(word);
    }



    public void computeTreeOfWords(ArrayDeque<Position> positionsDeque, String targetWord, int i) {
//        validateWord(positionsDeque, targetWord, i);
        if (positionsDeque.size() == targetWord.length()) {
            boolean is_valid = validateWord(positionsDeque, targetWord);
        }
        if (i == targetWord.length()) {
            return;
        }

        // initializing neighbors
        ArrayStack<Position> neighbors = new ArrayStack<>();
        char character = targetWord.charAt(i);
        neighbors = findValidNeighbors(positionsDeque.getLast(), "" + character);
//        System.out.println("Neighbors of '" + character  + "' at " + i);
//        printStackOfPositions(neighbors);


        if (neighbors.isEmpty()) {
            return;
        }

        while (!neighbors.isEmpty()) {
            positionsDeque.addLast(neighbors.pop());
            computeTreeOfWords(positionsDeque, targetWord, i + 1);
            positionsDeque.removeLast();
        }
//        return true;
    }
}
