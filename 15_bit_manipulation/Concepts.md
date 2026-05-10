# Bit Manipulation

## What Is Bit Manipulation?

Bit manipulation means working directly with the binary representation of numbers. Instead of thinking of a number like 5, you think of it as 101 in binary. Operations happen at the individual bit level, which makes them extremely fast.

In interviews, bit manipulation problems are usually either:
1. Math/logic tricks using bitwise operators
2. Using bits as a compact way to represent sets or states


## Why It Matters for Interviews

Bit manipulation shows up less frequently than arrays or trees, but when it does, it is very hard to figure out if you have never seen the patterns before. The good news is there are only a handful of core tricks. Once you know them, you can handle almost any bit problem.


## Bitwise Operators in Java

| Operator | Symbol | Example | Result |
|---|---|---|---|
| AND | & | 5 & 3 (101 & 011) | 1 (001) |
| OR | \| | 5 \| 3 (101 \| 011) | 7 (111) |
| XOR | ^ | 5 ^ 3 (101 ^ 011) | 6 (110) |
| NOT | ~ | ~5 (~00000101) | -6 (flips all bits) |
| Left Shift | << | 5 << 1 (101 << 1) | 10 (1010) |
| Right Shift | >> | 5 >> 1 (101 >> 1) | 2 (10) |


## Essential Bit Tricks

These are the building blocks. Memorize them.

```
n & (n - 1)     -> Removes the lowest set bit
                   (Used to count set bits, check power of 2)

n & (-n)        -> Isolates the lowest set bit
                   (Useful for finding the rightmost 1)

n ^ n = 0       -> XOR of a number with itself is 0
n ^ 0 = n       -> XOR with 0 gives the same number
                   (This is the basis for the "single number" trick)

n & 1           -> Checks if n is odd (last bit is 1)

n >> k & 1      -> Gets the k-th bit (0-indexed from right)

n | (1 << k)    -> Sets the k-th bit to 1
n & ~(1 << k)   -> Clears the k-th bit to 0
n ^ (1 << k)    -> Toggles the k-th bit
```


## XOR Properties -- The Most Important Ones

XOR is the star of bit manipulation problems. Key properties:

1. **Self-inverse:** a ^ a = 0
2. **Identity:** a ^ 0 = a
3. **Commutative:** a ^ b = b ^ a
4. **Associative:** (a ^ b) ^ c = a ^ (b ^ c)

This means if you XOR all numbers in an array where every number appears twice except one, all the pairs cancel out and you are left with the single number. This is the "Single Number" problem.


## Common Patterns

### Pattern 1: XOR to Find the Unique Element
If every element appears twice except one, XOR them all.

### Pattern 2: Count Set Bits (Hamming Weight)
Use n & (n - 1) to remove lowest set bit one at a time. Count iterations.

### Pattern 3: Power of Two Check
A power of 2 has exactly one set bit. So n & (n - 1) == 0 and n > 0.

### Pattern 4: Bit Masking for Subsets
A bitmask of length n can represent all subsets of an n-element set. Bit i being 1 means "include element i".

### Pattern 5: Reverse Bits / Counting Bits Array
Build the answer bit by bit using shifts.


## Problems to Practice

| Problem | Difficulty | Core Idea |
|---|---|---|
| Single Number (LC #136) | Easy | XOR all elements |
| Number of 1 Bits (LC #191) | Easy | n & (n - 1) trick |
| Counting Bits (LC #338) | Easy | DP with bit trick |
| Reverse Bits (LC #190) | Easy | Shift and build |
| Missing Number (LC #268) | Easy | XOR with indices |
| Sum of Two Integers (LC #371) | Medium | Bit-level addition |
| Reverse Integer (LC #7) | Medium | Math + overflow check |
