# LER_DSA -- Learn, Execute, Repeat

## One-Month Java DSA Sprint for Coding Interviews

**Language:** Java  
**Timeline:** 30 days  
**Suggested pace:** 2 hours on regular study days, 4 hours on deep-work days  
**Goal:** Learn the core DSA patterns, solve the right problems in the right order, and become ready for serious coding interview practice.

This is a self-contained Java DSA curriculum. It is written for anyone who wants a clear, practical, one-month path from fundamentals to interview-level problem solving.

The repo is not just a problem list. Each topic has:

- simple concept notes
- Java pattern templates
- a daily schedule
- a progress tracker
- revision rules
- interview communication guidance

Start anytime and follow the same Day 1 through Day 30 order.

---

## How to Use This Repo

For every topic, follow this loop:

1. Read `Concepts.md` until the idea feels simple.
2. Read or run `Patterns.java` to understand the Java template.
3. Solve the assigned problems from `STUDY_PLAN_DETAILED.md`.
4. Log every attempt in `progress_tracker.md`.
5. Redo every problem marked `[H]` or `[L]`.

The goal is not to memorize answers. The goal is to recognize patterns, explain tradeoffs, write clean Java, and test edge cases.

---

## The Sprint Strategy

The plan is designed for roughly **70 to 80 focused study hours**:

- regular study days: about 2 hours
- deep-work days: about 4 hours

That is enough for:

- 80 to 95 high-quality problem attempts
- all core DSA modules
- focused revision of weak patterns
- several timed sets or mock-style sessions
- a clean Java-first interview routine

This is an intense plan. It works only if the attempts are honest.

Good rules:

- Regular study days: one topic, one or two strong problems.
- Deep-work days: deeper practice, revision, and timed sets.
- Editorials are allowed only after a real attempt.
- A problem is not complete until you can explain the pattern and redo it later.

If your schedule is different, keep the order and shift the deep-work blocks to
the days where you have more time.

---

## Repository Structure

```
LER_DSA/
|
|-- README.md                         Sprint overview and repo map
|-- LICENSE                           MIT license
|-- STUDY_PLAN_DETAILED.md            30-day plan with exact daily work
|-- progress_tracker.md               Daily tracker, problem log, revision queue
|-- COVERAGE_AUDIT.md                 Topic coverage and what to prioritize
|-- PROBLEM_SOLVING_FRAMEWORK.md      Framework for solving any DSA problem
|-- PATTERN_RECOGNITION_GUIDE.md      Map problem clues to the right approach
|-- COMMON_MISTAKES.md                Bugs, pitfalls, and debugging checklist
|-- JAVA_CHEATSHEET.md                Java interview reference
|-- INTERVIEW_COMMUNICATION.md        How to talk while solving
|-- INTERVIEW_TIPS.md                 Interview preparation beyond DSA
|
|-- 00_fundamentals/
|-- 01_arrays_and_hashing/
|-- 02_two_pointers/
|-- 03_sliding_window/
|-- 04_binary_search/
|-- 05_stacks_and_queues/
|-- 06_linked_lists/
|-- 07_recursion_and_backtracking/
|-- 08_trees/
|-- 09_heaps/
|-- 10_graphs/
|-- 11_dynamic_programming/
|-- 12_greedy/
|-- 13_intervals/
|-- 14_tries/
|-- 15_bit_manipulation/
|-- 16_math_and_stats/
|-- 17_union_find/
|-- 18_strings/
|-- 19_sorting_and_matrix/
|-- 20_design/
```

Each numbered topic folder contains:

- `Concepts.md`: plain-language explanation, patterns, and practice set
- `Patterns.java`: reusable Java examples and templates

---

## What This Curriculum Covers

### Core Foundations

- Big-O analysis
- Java collections for DSA
- Arrays and HashMap
- Strings
- Sorting
- Matrix traversal

### Core Patterns

- Prefix sums
- Two pointers
- Sliding window
- Binary search
- Binary search on answer
- Stack and monotonic stack
- Linked list pointer manipulation
- Recursion and backtracking

### Core Data Structures

- Trees and BST
- Heaps and priority queues
- Graphs
- Union-Find
- Tries
- Design-style structures like LRU Cache

### Advanced Interview Patterns

- Greedy
- Intervals
- Dynamic programming
- Bit manipulation
- Math patterns
- Mixed timed practice

---

## 30-Day Roadmap

### Week 1 -- Foundation and Array Patterns

Big-O, Java collections, arrays, hashing, two pointers, sliding window, and binary search.

Target: 20 to 23 problems.

### Week 2 -- Linear Structures and Recursion

Stacks, queues, linked lists, recursion, backtracking, strings, sorting, and matrix.

Target: 20 to 23 problems.

### Week 3 -- Trees, Heaps, Greedy, Intervals

Tree DFS/BFS, BSTs, heaps, top-k problems, greedy decisions, and interval sorting.

Target: 20 to 24 problems.

### Week 4 -- Graphs, DP, Tries, Design, Review

Graphs, Union-Find, dynamic programming, tries, bits, math, design problems, and final revision.

Target: 20 to 25 problems.

---

## Daily Schedule

### Regular 2-Hour Block

```
00:00 - 00:10   Review yesterday's mistakes
00:10 - 00:30   Read today's concept notes
00:30 - 01:30   Solve the main problem
01:30 - 01:50   Read editorial only after trying
01:50 - 02:00   Log result and schedule revision
```

### Deep-Work 4-Hour Block

```
00:00 - 00:30   Review weak patterns
00:30 - 02:45   Solve 4 to 5 problems
02:45 - 03:25   Redo previous [H] or [L] problems
03:25 - 04:00   Timed set, mock-style run, or Java cleanup
```

---

## Rules That Matter

1. **Attempt first.** Spend at least 20 minutes on a medium before checking hints.
2. **Name the pattern.** Example: "prefix sum + HashMap", not just "used map".
3. **Explain before coding.** Say the brute force, then the optimized approach.
4. **Test edge cases.** Empty input, one element, duplicates, negatives, overflow.
5. **Redo misses.** A solved-after-editorial problem is not complete.
6. **Keep Java clean.** Clear loops beat clever code in interviews.

---

## What Interview Ready Means

You are ready for serious interview practice when you can:

- solve most easy problems in 10 to 15 minutes
- solve common medium problems in 25 to 35 minutes
- explain time and space complexity clearly
- recognize the main DSA patterns from problem wording
- finish a 45-minute timed session with working code
- redo previous `[H]` and `[L]` problems without looking

No curriculum can guarantee an offer. This repo gives the structure. The result comes from honest attempts, revision, and mocks.

---

## Main Files to Open

- Start here: `STUDY_PLAN_DETAILED.md`
- Track here: `progress_tracker.md`
- Check coverage: `COVERAGE_AUDIT.md`
- If stuck: `PATTERN_RECOGNITION_GUIDE.md`
- Before interviews: `INTERVIEW_COMMUNICATION.md`
- Java quick lookup: `JAVA_CHEATSHEET.md`

---

## Helpful Resources

- LeetCode: https://leetcode.com
- Visualgo: https://visualgo.net
- Big-O Cheat Sheet: https://www.bigocheatsheet.com

---

## Contributing

Contributions are welcome. If you want to improve explanations, add examples,
fix mistakes, or suggest better problem ordering:

1. Fork the repository.
2. Create a branch:

```bash
git checkout -b improve-topic-notes
```

3. Make your changes.
4. Commit and push:

```bash
git add .
git commit -m "Improve topic notes"
git push origin improve-topic-notes
```

5. Open a pull request to the `main` branch.

For small fixes, keep the pull request focused. For bigger changes, explain what
changed and why.

---

## Public or Private?

Keep the repository **public** if the goal is to share it on LinkedIn and allow
any learner to fork it, use it, and open pull requests.

Use **private** only if you want to restrict access. In a private repository,
people need explicit access before they can view the repo or contribute. For an
open learning resource, public is the better setup.

Suggested public setup:

- Keep visibility as public.
- Keep the MIT license.
- Review pull requests before merging.
- Protect the `main` branch later if the repo starts getting contributors.

To change repository visibility on GitHub:

1. Open the repository on GitHub.
2. Go to `Settings`.
3. Scroll to `Danger Zone`.
4. Choose `Change repository visibility`.
5. Select public or private and confirm.

For this project, public is recommended because it lets others discover the
repo, fork it, and send pull requests.

---

## License

This project is available under the MIT License. Use it, adapt it, and share it
for learning.
