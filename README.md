# Tokenize
Imlement `StringUtil` class methods

You can use following methods\classes:
- `String.split`
- `String.strip`
- `String.replace`
- `String.replaceAll`
- `String.toLowerCase`
- `String.equalsIgnoreCase`
- `String.startsWith`
- `String.matches`
- `String.join`
- `StringBuilder`
- `StringJoiner`

# countEqualIgnoreCase
```java
public static int countEqualIgnoreCase(String[] words, String sample) {
        throw new UnsupportedOperationException();
    }
```
Return the number of words from `words` array that are equal to `sample` with case ignored

If `sample` is `null` or `words` is `null` or empty return `0`
`words` is guaranteed to not contain `null` values

Example:
```java
String[] words = new String[] {"nice", "nICE", "nic3"};
String sample = "NICE";
int result = StringUtil.countEqualIgnoreCase(words, sample); // 2
```

# splitWords
```java
public static String[] splitWords(String text) {
        throw new UnsupportedOperationException();
    }
```
Split `text` string into array of words using following separating characters: `",", ".", ";", ":", "...", " "`

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
public static String convertPath(String path, boolean toWin) {
        throw new UnsupportedOperationException();
    }
```
Convert `path` to unix\windows path depending on a boolean parameter

We consider unix path as path in this format: `/folder/folder/file.txt`

And windows path as path in this format: `C:\\folder\\folder\\file.txt`

Let's consider unix `/` root folder to correspond to windows `C:` and vice versa 

Let's consider unix `~` path to correspond to windows `C:\\User` path

If `path` doesn't correspond neither to unix path nor to windows path return `null`

If `path` already corresponds to the required format return `path`

`path` parameter examples:
- `C:\\Program Files`
- `/dev/null`
- `file.txt`
- `folder/logs`
- `.\\to_do_list.txt`

Example:
```java
String winPath = "C:\\Program Files\\my_prog_file.py";
String unixPath = StringUtil.pathConvert(winPath, false); // "/Program Files/my_prog_file.py"
unixPath = "../script.sh";
winPath = StringUtil.pathConvert(unixPath, true); // "..\\script.sh"
unixPath = StringUtil.pathConvert(unixPath, false); // "../script.sh"
```
# joinWords
```java
public static String joinWords(String[] words) {
        throw new UnsupportedOperationException();
    }
```
Join words from `words` array and return as a string in the following format: `"[string, string, ..., string]"`

If `words` is `null` or empty return `null`
`words` is guaranteed to not contain `null` values
`words` may contain empty strings, ignore them, i. e. don't put them in the resulting string

Example:
```java
String[] words = new String[]{"go", "with", "the", "", "FLOW"};
String result = StringUtil.joinWords(words); // "[go, with, the, FLOW]"
```
