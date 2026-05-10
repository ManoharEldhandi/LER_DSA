# Strings -- Concepts

## Why Strings Matter

String problems are array problems with characters. Most of them use the same patterns:

- HashMap or `int[26]` for character counts
- Two pointers for palindromes
- Sliding window for substrings
- Stack for parsing nested or bracket-like structure
- Trie for prefixes
- DP for subsequences and palindrome substrings

In Java, `String` is immutable. That means every time you build a new string by repeatedly doing `s = s + c`, Java creates new objects. For repeated building, use `StringBuilder`.

---

## Java String Basics

Useful operations:

```java
char c = s.charAt(i);
int n = s.length();
String lower = s.toLowerCase();
char[] arr = s.toCharArray();
String joined = String.join("#", list);
```

For lowercase English letters, prefer:

```java
int[] count = new int[26];
count[c - 'a']++;
```

For general characters, use:

```java
Map<Character, Integer> count = new HashMap<>();
count.put(c, count.getOrDefault(c, 0) + 1);
```

---

## Core Patterns

### Pattern 1: Frequency Count

Use when the problem says anagram, permutation, same characters, or count occurrences.

Example:

`"listen"` and `"silent"` are anagrams because both have the same frequency of each character.

### Pattern 2: Two Pointers

Use when comparing from both ends.

Common clues:

- palindrome
- reverse words
- remove invalid characters
- sorted string/array

### Pattern 3: Sliding Window

Use when the problem asks for a substring.

Common clues:

- longest substring
- shortest substring
- contains all characters
- at most k distinct characters

### Pattern 4: Stack Parsing

Use when the string has nested structure.

Common clues:

- parentheses
- path simplification
- decode string
- calculator

### Pattern 5: DP on Strings

Use when the problem says subsequence, edit, count ways, or longest common.

Examples:

- Longest Common Subsequence
- Edit Distance
- Distinct Subsequences
- Palindromic Substrings

---

## Common Mistakes

- Confusing substring and subsequence.
- Forgetting that Java strings are immutable.
- Using `s.substring()` too freely in recursion and creating extra memory.
- Not handling uppercase, spaces, or punctuation in palindrome problems.
- Off-by-one bugs in sliding windows.
- Forgetting to shrink the window after it becomes valid or invalid.

---

## Problems to Practice

### Easy

1. Valid Palindrome (LC 125)
2. Valid Anagram (LC 242)
3. Longest Common Prefix (LC 14)
4. Valid Parentheses (LC 20)
5. Reverse Words in a String III (LC 557)
6. Valid Palindrome II (LC 680)

### Medium

1. Group Anagrams (LC 49)
2. Longest Substring Without Repeating Characters (LC 3)
3. Longest Repeating Character Replacement (LC 424)
4. Permutation in String (LC 567)
5. Find All Anagrams in a String (LC 438)
6. Simplify Path (LC 71)
7. Decode String (LC 394)
8. Generate Parentheses (LC 22)
9. Word Break (LC 139)
10. Palindromic Substrings (LC 647)

### Hard

1. Minimum Window Substring (LC 76)
2. Basic Calculator (LC 224)
3. Edit Distance (LC 72)
4. Distinct Subsequences (LC 115)
5. Text Justification (LC 68)

---

## Interview Shortcut

When you see a string problem, ask:

1. Is it about characters and counts? Use frequency.
2. Is it about a substring? Use sliding window.
3. Is it about palindrome? Use two pointers or DP.
4. Is it about nesting? Use stack.
5. Is it about subsequence or edit? Use DP.
