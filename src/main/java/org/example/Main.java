package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        // Check if file path is provided as command line argument
        if (args.length < 1) {
            System.out.println("Please provide the file path as a command line argument.");
            return;
        }

        String filePath = args[0];

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            Puzzle puzzle;

            // Process each chunk of data in the file
            while ((puzzle = Puzzle.parseChunk(reader)) != null) {
                // Printing the puzzle
                System.out.println("Puzzle: ");
                puzzle.printPuzzle();
                System.out.println("Words: " + puzzle.getWords());

                // Testing finding character occurrences
//                testFindOccurrences(puzzle, "a");
                System.out.println(puzzle.getStringAtPosition(puzzle.findAllOccurrencesOfCharacter("e").top()));
//                testFindOccurrences(puzzle, "e");
//                testFindOccurrences(puzzle, "i");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Helper method to test finding character occurrences
    private static void testFindOccurrences(Puzzle puzzle, String character) {
        // Call the findAllOccurrencesOfCharacter method from the Puzzle class
        ArrayStack<Position> positions = puzzle.findAllOccurrencesOfCharacter(character);

        // Check if any occurrences were found
        if (positions.size() > 0) {
            System.out.println("Occurrences of '" + character + "' found at positions: ");

            // Loop through all the positions in the stack and print them
            while (!positions.isEmpty()) {
                Position pos = positions.pop();
                System.out.println("Row: " + pos.getRow() + ", Column: " + pos.getColumn());
            }
        } else {
            System.out.println("No occurrences of '" + character + "' found in the puzzle.");
        }
    }



}
