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

        // Define characters to find
        String firstCharacter = "e";
        String secondCharacter = "v";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            Puzzle puzzle;

            // Process each chunk of data in the file
            while ((puzzle = Puzzle.parseChunk(reader)) != null) {
                // Printing the puzzle
                System.out.println("Puzzle: ");
                puzzle.printPuzzle();
                System.out.println("Words: " + puzzle.getWords());

                // Find all occurrences of firstCharacter
                ArrayStack<Position> firstCharacterPositions = puzzle.findAllOccurrencesOfCharacter(firstCharacter);

                // If there is at least one occurrence of firstCharacter
                if (!firstCharacterPositions.isEmpty()) {
                    // Get the top position of firstCharacter
                    Position firstCharacterPosition = firstCharacterPositions.top();
                    System.out.println("Row " + firstCharacterPosition.getRow() + ", Column " + firstCharacterPosition.getColumn());

                    System.out.println("Found a(n) '" + firstCharacter + "' at: Row " + firstCharacterPosition.getRow() + ", Column " + firstCharacterPosition.getColumn());

                    // Find valid neighbors with secondCharacter from the position of firstCharacter
                    ArrayStack<Position> secondCharacterNeighbors = puzzle.findValidNeighbors(firstCharacterPosition, secondCharacter);

                    // Print the result
                    System.out.println("Valid neighbors with '" + secondCharacter + "' around the first '" + firstCharacter + "':");

                    while (!secondCharacterNeighbors.isEmpty()) {
                        Position secondCharacterPosition = secondCharacterNeighbors.pop();
                        System.out.println("Row " + secondCharacterPosition.getRow() + ", Column " + secondCharacterPosition.getColumn());
                    }
                } else {
                    System.out.println("No '" + firstCharacter + "' found in the puzzle.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
                // Testing finding character occurrences
//                testFindOccurrences(puzzle, "a");
//                System.out.println(puzzle.getStringAtPosition(puzzle.findAllOccurrencesOfCharacter("e").top()));
//                testFindOccurrences(puzzle, "e");
//                testFindOccurrences(puzzle, "i");


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
