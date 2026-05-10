# Math and Statistics for Interviews

## Why This Section Exists

Math problems in coding interviews are not about solving complex equations. They are about recognizing mathematical properties and using them to write efficient code. These problems test whether you can spot a pattern or shortcut instead of brute-forcing.

Since you are interested in ML, having strong math fundamentals also helps with understanding algorithms, probability, and statistical reasoning.


## Common Math Concepts in Interviews

### 1. Modular Arithmetic

When numbers get huge, problems often say "return the answer modulo 10^9 + 7." This prevents integer overflow and tests if you understand modular arithmetic.

Key rules:
```
(a + b) % m = ((a % m) + (b % m)) % m
(a * b) % m = ((a % m) * (b % m)) % m
(a - b) % m = ((a % m) - (b % m) + m) % m  (add m to handle negatives)
```

In Java, use `long` when doing modular arithmetic to avoid overflow during intermediate steps.


### 2. GCD and LCM

```
GCD(a, b) = GCD(b, a % b), base case GCD(a, 0) = a  (Euclidean algorithm)
LCM(a, b) = (a * b) / GCD(a, b)
```

These come up in fraction simplification, scheduling, and number theory problems.


### 3. Prime Numbers

Sieve of Eratosthenes gives all primes up to n in O(n log log n).

Used in: counting primes, factorization, cryptography-related problems.


### 4. Power and Exponentiation

Fast power (binary exponentiation): compute a^n in O(log n) instead of O(n).

Used when you need to compute large powers, especially with modular arithmetic.


### 5. Combinatorics

- n choose k = n! / (k! * (n-k)!)
- Pascal's triangle: C(n, k) = C(n-1, k-1) + C(n-1, k)
- Catalan numbers: C(n) = C(2n, n) / (n + 1) -- counts number of valid parentheses, BSTs, etc.

You rarely need to memorize formulas, but you should recognize when a problem is asking for combinations or permutations.


### 6. Probability Basics (Useful for ML Too)

- P(A or B) = P(A) + P(B) - P(A and B)
- P(A and B) = P(A) * P(B|A)
- Expected value E[X] = sum of (value * probability)
- Reservoir sampling: randomly select k items from a stream of unknown size


## Common Interview Patterns

### Pattern 1: Detect Duplicates with Math
Use sum formula, XOR, or Floyd's cycle detection instead of extra space.

### Pattern 2: Matrix Rotation / Spiral
Pure index manipulation. No special data structures needed.

### Pattern 3: Random Selection
Reservoir sampling, Fisher-Yates shuffle, weighted random selection.

### Pattern 4: Number Properties
Happy number (digit sum cycle), palindrome number, power of two/three.


## Problems to Practice

| Problem | Difficulty | Core Idea |
|---|---|---|
| Happy Number (LC #202) | Easy | Cycle detection on digit sum |
| Plus One (LC #66) | Easy | Handle carry |
| Pow(x, n) (LC #50) | Medium | Binary exponentiation |
| Multiply Strings (LC #43) | Medium | Grade-school multiplication |
| Rotate Image (LC #48) | Medium | Transpose + reverse |
| Spiral Matrix (LC #54) | Medium | Boundary shrinking |
| Set Matrix Zeroes (LC #73) | Medium | Use first row/col as markers |
| Count Primes (LC #204) | Medium | Sieve of Eratosthenes |
