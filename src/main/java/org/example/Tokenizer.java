package org.example;

public interface Tokenizer {
    static Tokenizer getNewInstance() {
        throw new UnsupportedOperationException();
    }

    void load(String text);

    String getWord(int word);

    int getWordsCnt();
}