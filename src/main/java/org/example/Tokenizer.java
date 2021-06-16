package org.example;

public interface Tokenizer {
    static Tokenizer getNewInstance() {
        return new MyTokenizer();
    }

    void load(String text);

    String getWord(int word);

    int getWordsCnt();
}