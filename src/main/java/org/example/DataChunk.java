package org.example;

import java.util.List;

public class DataChunk implements IDataChunk {
    private final List<List<String>> table;
    private final List<String> words;

    public DataChunk(List<List<String>> table, List<String> words) {
        this.table = table;
        this.words = words;
    }

    @Override
    public List<List<String>> getTable() {
        return table;
    }

    @Override
    public List<String> getWords() {
        return words;
    }
}
