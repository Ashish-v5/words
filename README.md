# Tokenizer
Make a class that implements **Tokenizer** interface. Tokenizer can load a text as string and separate it into words.
 The interface has following methods:
- getNewInstance - make and return new instance of your class
- load - load string object as text containing words separated by special characters
- getWord - get n-th word from the loaded text, n is 0 for first word
- getWordsCnt - return the number of separated words from the loaded text

Make sure that:
- getNewIntance method really makes new instance of your class that doesn't have loaded text yet
- getWord and getWordsCnt methods throw IllegalStateException if load method hasn't been called yet
- getWord method throw IllegalArgumentException if n is negative or >= getWordsCnt
- load method throws IllegalArgumentException for null argument and doesn't throw for empty string
a sequence of separating characters is treated as single separator (i. e. there are no words between them)
- empty string is treated as single word
- string consisting only of separating characters is treated as containing 0 words
- 
- string containing words and starting with separating characters is treated as having empty word at 0-th position

Example:
```java
String text = " ,. Words, some words. ";
Tokenizer instance = Tokenizer.getNewInstance();
instance.getWordsCnt() // throws IllegalStateException
instance.load(text); // tokenizer processed the text as follows: ["", "Words", "some", "words"]
instance.getWordsCnt(); // returns 4
instance.getWord(2) // returns "some"
instance.getWord(0) // returns ""
```
Separating characters: ",", ".", ";", ":", "...", " "
