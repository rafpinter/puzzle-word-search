package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java org.example.Main <input-file-path>");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            ITextFileParser parser = new TextFileParser();

            while (true) {
                IDataChunk chunk = parser.parseChunk(reader);
                if (chunk == null) {
                    break; // End of file reached
                }

                // Additional processing can be done here using the chunk data...
                // Example: Print the data
                printChunkData(chunk);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printChunkData(IDataChunk chunk) {
        System.out.println("Table Data:");
        for (List<String> row : chunk.getTable()) {
            System.out.println(row);
        }

        System.out.println("Word List:");
        System.out.println(chunk.getWords());
        System.out.println("--------------");
    }
}
