package org.example;

import java.io.BufferedReader;
import java.io.IOException;

public interface ITextFileParser {
    IDataChunk parseChunk(BufferedReader reader) throws IOException;
}
