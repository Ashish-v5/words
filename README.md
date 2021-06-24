# Tokenize
Imlement [`StringUtil`](src/main/java/org/example/StringUtil.java) class methods:
- `countEqualIgnoreCaseAndSpaces`
- `splitWords`
- `convertPath`
- `joinWords`

All these methods have default implementation that throws `UnsupportedOperationException`.\
You have to change each method's body so that it behaves as it's required.

We made JUnit5 tests in [`StringUtilTest`](src/test/java/org/example/StringUtilTest.java) class for making sure each method's behaviour is correct.\
You can use these tests yourself, for that you need to install [Maven](https://maven.apache.org/) project manager and run `mvn clean test` in the project folder.

While imlementing the methods you might need to come up with `regular expressions`. You may consider using [regex101.com](https://regex101.com/) to easier design of regular expressions.

You can and should use following methods\classes (click on the name):
- [`String.strip`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/String.html#strip())
- [`String.split`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/String.html#split(java.lang.String))
- [`String.replaceAll`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/String.html#replaceAll(java.lang.String,java.lang.String))
- [`String.replaceFirst`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/String.html#replaceFirst(java.lang.String,java.lang.String))
- [`String.toLowerCase`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/String.html#toLowerCase())
- [`String.equalsIgnoreCase`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/String.html#equalsIgnoreCase(java.lang.String))
- [`String.startsWith`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/String.html#startsWith(java.lang.String))
- [`String.matches`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/String.html#matches(java.lang.String))
- [`String.join`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/String.html#join(java.lang.CharSequence,java.lang.CharSequence...))
- [`StringBuilder`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/StringBuilder.html)
- [`StringJoiner`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/StringJoiner.html)
- [`StringTokenizer`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/StringTokenizer.html)

# countEqualIgnoreCaseAndSpaces
```java
public static int countEqualIgnoreCaseAndSpaces(String[] words, String sample)
```
Return the number of words from `words` array that are equal to `sample` ignoring characters case and leading and trailing spaces

If `sample` is `null` or `words` is `null` or empty, return `0`

`words` is guaranteed to not contain `null` values

Example:
```java
String[] words = new String[] {"   nice ", "nICE", "nic3"};
String sample = "NICE";
int result = StringUtil.countEqualIgnoreCaseAndSpaces(words, sample); // 2
words = new String[]{" zoOm ", " z oom", " Z O O M "};
sample = "ZOOM";
result = StringUtil.countEqualIgnoreCaseAndSpaces(words, sample); // 1
```

# splitWords
```java
public static String[] splitWords(String text)
```
Split `text` string into array of words using following separating characters: `",", ".", ";", ":", "...", " ", "?", "!"`

For empty string, `null` string, and string consisting only of separating characters return `null`

Example:
```java
String text = " go with ...the:;        FLOW ";
String[] result = StringUtil.splitWords(text); // ["go", "with", "the", "FLOW"]
text = ":..,,,::: ;;;      ";
result = StringUtil.splitWords(text); // null
```

# convertPath
```java
public static String convertPath(String path, boolean toWin)
```
Convert `path` to unix\windows path depending on a boolean parameter

We consider unix path as path in this format: `/folder/folder/file.txt`

And windows path as path in this format: `C:\folder\folder\file.txt`

Let's consider unix `/` root folder to correspond to windows `C:` and vice versa 

Let's consider unix `~` path to correspond to windows `C:\User` path

If `path` is `null`, empty, or doesn't correspond neither to unix path nor to windows path return `null`

If `path` already corresponds to the required format return `path`

`path` parameter examples:
- `C:\Program Files`
- `/dev/null`
- `file.txt`
- `folder/logs`
- `/home//user///some_logs`
- `.\to_do_list.txt`

Example:
```java
String winPath = "C:\\Program Files\\my_prog_file.py";
String unixPath = StringUtil.convertPath(winPath, false); // "/Program Files/my_prog_file.py"
unixPath = "../script.sh";
winPath = StringUtil.convertPath(unixPath, true); // "..\\script.sh"
unixPath = StringUtil.convertPath(unixPath, false); // "../script.sh"
unixPath = "//home/user/somefile";
winPath = StringUtil.convertPath(unixPath, true); // "C:\\home\\user\\somefile"
```
# joinWords
```java
public static String joinWords(String[] words)
```
Join words from `words` array and return as a string in the following format: `"[str_1, str_2, ..., str_n]"`

If `words` is `null` or empty return `null`

`words` is guaranteed to not contain `null` values

`words` may contain empty strings, ignore them, i. e. don't put them in the resulting string

If `words` contains only empty strings return `null`

Example:
```java
String[] words = new String[]{"go", "with", "the", "", "FLOW"};
String result = StringUtil.joinWords(words); // "[go, with, the, FLOW]"
```
