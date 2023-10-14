package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Check if filename is provided
        if(args.length < 1) {
            System.out.println("Please provide the filename as a command-line argument.");
            return;
        }

        // Try to create a buffered reader for the file and parse the puzzles
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            while (true) {
                // Check if there are more puzzles to parse
                reader.mark(1);
                if (reader.read() == -1) {
                    break;
                }
                reader.reset();

                // Parse a puzzle and perform some operations
                Puzzle puzzle = Puzzle.parsePuzzle(reader);
                processPuzzle(puzzle);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Processes a parsed puzzle.
     * For this example, it prints the table and the words of the puzzle.
     *
     * @param puzzle The puzzle to be processed.
     */
    private static void processPuzzle(Puzzle puzzle) {
        // Print the puzzle
//        System.out.println("Puzzle:");
//        for (int i = 0; i < puzzle.getNumberOfRows(); i++) {
//            for (int j = 0; j < puzzle.getNumberOfColumns(); j++) {
//                System.out.print(puzzle.getElement(i, j) + " ");
//            }
//            System.out.println();
//        }

        // Print the words
//        System.out.println("Words:");
//        List<String> words = puzzle.getWords();
//        for (String word : words) {
//            System.out.println(word);
//        }

        // Add an empty line for better readability when printing multiple puzzles
        System.out.println();
    }
}
