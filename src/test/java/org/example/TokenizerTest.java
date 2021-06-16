package org.example;


import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class TokenizerTest
{
    private static Tokenizer instance;
    private static final String[] separators = new String[]{", ", " ", ",", ":", ": ", "; ", ";",
            ".", ". ", "!", "! ", "...", "... "};

    @BeforeEach
    void makeNewInstance() {
        instance = Tokenizer.getNewInstance();
    }

    @Test
    void testMakesNewInstance() {
        instance = Tokenizer.getNewInstance();
        assertNotSame(instance, Tokenizer.getNewInstance());
    }

    @Test
    void testMakesNotNullInstance() {
        assertNotNull(Tokenizer.getNewInstance());
    }

    @Test
    void testThrowsWhenLoadingNull() {
        assertThrows(IllegalArgumentException.class, () -> instance.load(null));
    }

    @Test
    void textDoesntThrowWhenLoadingEmpty() {
        assertDoesNotThrow(() -> instance.load(""));
    }

    @Test
    void testThrowsWhenGettingWordWithNegativeArg() {
        instance.load("text");
        assertThrows(IllegalArgumentException.class, () -> instance.getWord(-3));
    }

    @Test
    void testThrowsWhenGettingWordWithTooBigArg() {
        String text = "First";
        instance.load(text);
        assertThrows(IllegalArgumentException.class, () -> instance.getWord(1));
    }

    @Test
    void testThrowsWhenGettingWordWithTooBigArg2() {
        String text = "Words, some words. ";
        instance.load(text);
        assertThrows(IllegalArgumentException.class, () -> instance.getWord(3));
    }

    @Test
    void testReturnsWhenGettingWordFromEmpty() {
        String text = "";
        instance.load(text);
        assertEquals(text, instance.getWord(0));
    }

    @ParameterizedTest
    @MethodSource("makeStraightSeparatorsStrings")
    void testReturnsWhenGettingWordFromSeparatingCharactersText(String sepStr) {
        instance.load(sepStr);
        assertEquals(0, instance.getWordsCnt());
        assertThrows(IllegalArgumentException.class, () -> instance.getWord(0));
    }

    static Stream<String> makeStraightSeparatorsStrings() {
        StringBuilder builder = new StringBuilder();
        Stream.Builder<String> argBuilder = Stream.builder();
        for (String sep : separators) {
            builder.append(sep);
            argBuilder.add(builder.toString());
        }

        argBuilder.add("  !!! ,, ;; ;; , !!! ....  ... .. . ...   ??  . ....   ");
        argBuilder.add("       ");
        argBuilder.add("...............");
        argBuilder.add("?!?!!!!?...!....!      ");
        argBuilder.add(" ! ! ! ? ? ");

        return argBuilder.build();
    }


    @Test
    void testReturnsWhenGettingWordFromSingleWordText() {
        String text = "example";
        instance.load(text);
        assertEquals(text, instance.getWord(0));
    }

    @Test
    void testReturnsWhenGettingWordFromTextStartingWithSeparator() {
        String text = " word";
        String expectedWord = "";
        instance.load(text);
        assertEquals(expectedWord, instance.getWord(0));
    }

    @Test
    void testReturnsWhenGettingWordFromTextStartingWithSeparator2() {
        String text = " word";
        String expectedWord = "word";
        instance.load(text);
        assertEquals(expectedWord, instance.getWord(1));
    }

    @Test
    void testReturnsWhenGettingWordFromTextWithUpperCaseWords() {
        String text = " YELL STOP WORD;RAKE... WELL: HINT; STAY! ALIVE.";
        String expectedWord = "HINT";
        instance.load(text);
        assertEquals(expectedWord, instance.getWord(6));
    }

    @Test
    void testReturnsWhenGettingWordFromTextWithNumbers() {
        String text = "123...495;;024, 77, 1231, 55";
        String expectedWord = "1231";
        instance.load(text);
        assertEquals(expectedWord, instance.getWord(4));
    }

    @Test
    void testReturnsWhenGettingWordFromTextContainingStraightSeparators() {
        String text = "a      b  c";
        String expectedWord = "b";
        instance.load(text);
        assertEquals(expectedWord, instance.getWord(1));
    }

    @Test
    void testReturnsWhenGettingWordFromTextContainingStraightSeparators2() {
        String text = "a      b  c";
        String expectedWord = "c";
        instance.load(text);
        assertEquals(expectedWord, instance.getWord(2));
    }

    @Test
    void testReturnsWhenGettingWordAfterReloadingText() {
        String text = "initial text";
        String expectedWord = "text";
        instance.load(text);
        assertEquals(expectedWord, instance.getWord(1));
        text = "new reloaded text";
        expectedWord = "reloaded";
        instance.load(text);
        assertEquals(expectedWord, instance.getWord(1));
    }

    @ParameterizedTest
    @MethodSource("makeTextWordsPairs")
    void textReturnsWhenGettingWord(String text, String[] words) {
        instance.load(text);
        for (int currWord = 0; currWord < words.length; ++currWord) {
            String word = words[currWord];
            assertEquals(word, instance.getWord(currWord));
        }
    }

    static Stream<Arguments> makeTextWordsPairs() {
        String text = " ;:a ,,,  b ::; c,d:e    f:g::;;;::::    h            ";
        String[] words = new String[]{"", "a", "b", "c", "d", "e", "f", "g", "h"};
        Stream.Builder<Arguments> argBuilder = Stream.builder();
        argBuilder.add(
                Arguments.arguments(text, words)
        );

        words = new String[] {"Spot", "of", "come", "to", "ever", "hand", "as", "lady", "meet", "on",
                "Get", "by" , "in", "cars", "picture", "ten", "street", "belt",
                "Stun", "smash", "critical", "jump", "strike", "flag"};

        StringBuilder builder = new StringBuilder();
        int sepInd = 0;
        for (String s : words) {
            builder.append(s);
            builder.append(separators[sepInd]);
            sepInd = (sepInd + 1) % separators.length;
        }

        text = builder.toString();
        argBuilder.add(
                Arguments.arguments(text, words)
        );

        return argBuilder.build();
    }

    @Test
    void testReturnsWhenGettingWordsCountForEmptyString() {
        String text = "";
        instance.load(text);
        assertEquals(1, instance.getWordsCnt());
    }

    @Test
    void testReturnsWhenGettingWordsCountForText() {
        String text = "Words, some words. ";
        instance.load(text);
        assertEquals(3, instance.getWordsCnt());
    }

    @Test
    void testReturnsWhenGettingWordsCountForTextStartingWithSeparator() {
        String text = " Words, some words. ";
        instance.load(text);
        assertEquals(4, instance.getWordsCnt());
    }

    @Test
    void testReturnsWhenGettingWordsCountAfterReloadingText() {
        String text = " Words, some words. ";
        instance.load(text);
        assertEquals(4, instance.getWordsCnt());

        String nextText = "one two";
        instance.load(nextText);
        assertEquals(2, instance.getWordsCnt());
    }
}
