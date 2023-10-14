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
                testFindOccurrences(puzzle, "e");
//                testFindOccurrences(puzzle, "i");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Helper method to test finding character occurrences
    public static void testFindOccurrences(Puzzle puzzle, String character) {
        System.out.println("Searching for character: " + character);

        // Ensure input character string is of length 1
        if (character != null && character.length() == 1) {
            ArrayStack<Position> positions = puzzle.findAllOccurrencesOfCharacter(character);

            // Check if the stack is not empty
            if (!positions.isEmpty()) {
                System.out.println("Positions of character '" + character + "': ");
                while (!positions.isEmpty()) {
                    System.out.println(positions.pop());
                }
            } else {
                System.out.println("Character '" + character + "' not found in the puzzle.");
            }
        } else {
            System.out.println("Invalid input: single character expected.");
        }
        System.out.println();
    }
}
