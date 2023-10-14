package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextFileParser implements ITextFileParser {

    @Override
    public IDataChunk parseChunk(BufferedReader reader) throws IOException {
        String firstLine = reader.readLine();
        if (firstLine == null) {
            return null;
        }
        String[] dimensions = firstLine.trim().split(" ");
        int nRows = Integer.parseInt(dimensions[0]);

        List<List<String>> tableData = new ArrayList<>();
        for (int i = 0; i < nRows; i++) {
            String line = reader.readLine();
            if (line == null) {
                throw new IOException("Unexpected end of file when reading table data");
            }
            List<String> row = new ArrayList<>(Arrays.asList(line.trim().split(" ")));
            tableData.add(row);
        }

        String wordLine = reader.readLine();
        if (wordLine == null) {
            throw new IOException("Unexpected end of file when reading word list");
        }
        List<String> wordData = new ArrayList<>(Arrays.asList(wordLine.trim().split(" ")));

        return new DataChunk(tableData, wordData);
    }
}
