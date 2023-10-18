package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;

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


            int puzzleCounter = 1;
            // Process each chunk of data in the file
            while ((puzzle = Puzzle.parseChunk(reader)) != null) {

                System.out.println("Query " + puzzleCounter + ":");
//                // Printing the puzzle
//                System.out.println("Puzzle: ");
//                puzzle.printPuzzle();
//                System.out.println("Words: " + puzzle.getWords());

                // Word to find - loop
                for (String word : puzzle.getWords()) {
                    // Your logic here using the word
                    System.out.println("Searching for: " + word);  // Example: Just printing the word

                    // first character
                    String firstCharacter = String.valueOf(word.charAt(0));
                    ArrayStack<Position> firstCharacterPositions = puzzle.findAllOccurrencesOfCharacter(firstCharacter);

                    while (!firstCharacterPositions.isEmpty()){
                        // pegando a primeira posição
                        Position firstPosition = firstCharacterPositions.pop();

                        // adicionando a stack
                        ArrayDeque<Position> positions = new ArrayDeque<>();
                        positions.addFirst(firstPosition);

                        for (int i = 1; i < word.length(); i++) {
                            puzzle.computeTreeOfWords(positions, word, i);
                        }
                    }
                }
                puzzleCounter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
