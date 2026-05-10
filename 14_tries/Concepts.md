# Tries (Prefix Trees)

## What Is a Trie?

A trie (pronounced "try") is a tree-like data structure used to efficiently store and retrieve strings, specifically when you need prefix-based lookups. Each node represents a single character, and paths from root to nodes represent prefixes of stored words.

Think of it like a dictionary where you flip pages by letter. If you are looking for "apple", you first go to the 'a' section, then look under 'ap', then 'app', and so on. That is exactly how a trie works.


## When to Use a Trie

- Autocomplete systems (type "app" and get "apple", "application", "append")
- Spell checkers
- Word search in a grid (using trie makes it way faster than brute force)
- IP routing (longest prefix matching)
- Counting words with a given prefix
- Problems that say "search for a word" or "starts with a prefix"


## How a Trie Works

```
       root
      / | \
     a   b   c
     |   |
     p   a
    / \   \
   p   e   d
   |
   l
   |
   e (end of "apple")
```

Each node has:
- An array or map of children (one for each possible character)
- A boolean flag indicating "a word ends here"


## Trie Node Structure in Java

```java
class TrieNode {
    TrieNode[] children = new TrieNode[26]; // for lowercase English letters
    boolean isEnd = false;
}
```

If the character set is larger (Unicode, etc.), use a HashMap instead of an array.


## Time Complexity

| Operation | Time | Space |
|---|---|---|
| Insert a word | O(L) | O(L) |
| Search a word | O(L) | O(1) |
| Search a prefix | O(L) | O(1) |

Where L = length of the word.

Compare this to a HashSet where insert and lookup are O(L) on average too. The advantage of a trie is prefix operations -- a HashSet cannot efficiently find "all words starting with 'app'."


## Trie vs HashSet

Use a HashSet when you only need exact word lookup.
Use a Trie when you need:
- Prefix matching
- Autocomplete
- Word-by-word building (like in word search problems)
- Wildcard matching (like '.' matching any character)


## Common Interview Patterns

### Pattern 1: Implement Trie (Insert, Search, StartsWith)
The foundation. Every other trie problem builds on this.

### Pattern 2: Word Search II (Backtracking + Trie)
Given a grid and a list of words, find all words in the grid. Use a trie to store all target words, then DFS through the grid using the trie to prune paths.

### Pattern 3: Design Add and Search Words (Wildcard Search)
Like a trie but '.' can match any character. Use DFS to branch at wildcard nodes.


## Problems to Practice

| Problem | Difficulty | Core Idea |
|---|---|---|
| Implement Trie (LC #208) | Medium | Build trie with insert/search/prefix |
| Design Add and Search Words (LC #211) | Medium | Trie + DFS for wildcard '.' |
| Word Search II (LC #212) | Hard | Trie + backtracking on grid |
| Longest Word in Dictionary (LC #720) | Medium | Trie + BFS/DFS |
| Search Suggestions System (LC #1268) | Medium | Trie-based autocomplete |
