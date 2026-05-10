# Greedy Algorithms

## What Is a Greedy Algorithm?

A greedy algorithm makes the locally optimal choice at every step, hoping that these local choices lead to a globally optimal solution. You pick the best option right now without worrying about the future.

Think of it like eating at a buffet. A greedy approach says "at each round, grab the most delicious item on the table." Sometimes that works out perfectly and you end up with the best possible meal. Other times, you should have saved room for dessert.

The key question with greedy is: does making the best local choice always lead to the best global result? If yes, greedy works. If no, you probably need DP or some other approach.


## How Greedy Differs From Dynamic Programming

This is one of the most common interview confusions, so let's be clear:

- **Dynamic Programming:** Explores all possibilities, stores results, guarantees optimal answer. Takes more time and space.
- **Greedy:** Makes one choice per step, never looks back. Faster and simpler, but only works when the greedy choice property holds.

The greedy choice property means: a globally optimal solution can be built by choosing the locally optimal option at each step.


## When to Use Greedy

Look for these clues in a problem:

1. The problem asks for a minimum or maximum of something
2. You can sort the input and process it in order
3. Making the "best" choice at each step does not invalidate future choices
4. The problem has optimal substructure (optimal solution contains optimal solutions to subproblems)
5. Key phrases: "minimum number of", "maximum profit", "fewest operations"

Common greedy problem types:
- Interval scheduling (which meetings to attend, merge intervals)
- Activity selection
- Jump games
- Gas station problems
- Assigning tasks


## Greedy Proof Techniques

In interviews, you usually don't need a formal proof, but you should be able to explain WHY greedy works for the problem. Two common reasoning approaches:

### Exchange Argument
If we have a non-greedy solution, we can swap elements to make it greedier without making it worse. This shows greedy is at least as good as any other solution.

### Greedy Stays Ahead
At every step, the greedy solution is at least as good as any other solution. So at the end, it must be optimal.

In an interview, just say something like: "The greedy choice works here because choosing X at this step can never make the overall answer worse, and it might make it better."


## Classic Greedy Patterns

### Pattern 1: Sort Then Greedily Process
Most greedy problems involve sorting first. The sorting gives you an order that makes the greedy choice obvious.

Example: Merge intervals -- sort by start time, then greedily merge overlapping ones.

### Pattern 2: Two Choices -- Take or Skip
At each element, decide to take it or skip it based on some simple rule.

Example: Jump Game -- at each position, track the farthest you can reach.

### Pattern 3: Always Pick the Extreme
Pick the largest, smallest, earliest, or latest element.

Example: Assign cookies -- sort both children's greed and cookie sizes, match smallest cookies to least greedy children.

### Pattern 4: Interval Scheduling
Sort intervals by end time. Pick the one that ends earliest. Remove all conflicting ones. Repeat.


## Greedy vs DP: How to Tell the Difference

| Scenario | Likely Greedy | Likely DP |
|---|---|---|
| Can you sort and process linearly? | Yes | -- |
| Does making one choice affect future choices in complex ways? | -- | Yes |
| Is there one obvious "best" choice at each step? | Yes | -- |
| Do you need to try multiple combinations? | -- | Yes |
| Is the input a set of intervals? | Often yes | -- |
| Does the problem involve subsequences with constraints? | -- | Often yes |


## Interview Tips

1. If you think greedy works, explain your reasoning. Don't just say "it's greedy."
2. Walk through an example to show the greedy choice works.
3. If the interviewer asks "can you prove it?", use the exchange argument or greedy-stays-ahead reasoning.
4. If greedy does NOT work, it usually means the local optimal choice can lead to a globally bad outcome. In that case, switch to DP.
5. Greedy solutions are usually O(n log n) because of sorting, or O(n) if the input is already sorted.


## Problems to Practice

| Problem | Difficulty | Core Idea |
|---|---|---|
| Jump Game (LC #55) | Medium | Track farthest reachable |
| Jump Game II (LC #45) | Medium | BFS-like greedy jumps |
| Gas Station (LC #134) | Medium | If total gas >= total cost, solution exists |
| Hand of Straights (LC #846) | Medium | Sort + greedily form groups |
| Merge Triplets (LC #1899) | Medium | Filter valid triplets |
| Partition Labels (LC #763) | Medium | Track last occurrence |
| Valid Parenthesis String (LC #678) | Medium | Track range of open counts |
