# Interview Communication Guide

This document is about how to talk during a technical interview. Solving the
problem is only half the battle. How you communicate your thought process is
equally important. Many candidates who solve the problem still get rejected
because the interviewer could not follow their thinking.


---


## The Opening (After Reading the Problem)

Restate the problem in your own words. This shows you understand it and gives
the interviewer a chance to correct any misunderstanding.

  "So if I understand correctly, we are given an array of integers and a target
  value, and we need to return the indices of the two numbers that add up to
  the target. Each input has exactly one solution, and I cannot use the same
  element twice. Is that right?"

Then ask clarifying questions:
  "Can the array contain negative numbers?"
  "Can there be duplicate values?"
  "Is the array sorted?"
  "What should I return if no solution exists?"


---


## Before You Start Coding

Explain your approach before writing any code. The interviewer should know
what you are about to do and why.

  "I am thinking of two approaches. The brute force would be to check every
  pair, which is O(n squared). But we can do better with a HashMap -- for
  each number, we check if the complement (target minus current number) is
  already in the map. This gives us O(n) time and O(n) space. Should I go
  ahead and code this?"

Wait for the interviewer to agree before you start coding. Sometimes they
want you to explore both approaches, or they might push you toward a
specific one.


---


## While You Are Coding

Narrate what you are doing. You do not need to explain every semicolon, but
the interviewer should be able to follow your logic.

Good examples:
  "I am initializing a HashMap to store each value and its index as I go..."
  "Now I loop through the array. For each element, I calculate the complement..."
  "Here I check if the complement exists in the map. If it does, I have found
  my answer..."
  "If we get through the loop without finding a pair, I return an empty array
  to indicate no solution..."

Bad examples:
  (Silence for 5 minutes while typing)
  "Uh... let me just... okay... hmm..."


---


## After You Finish Coding

Do not say "I am done." Instead, verify your solution:

  "Let me trace through this with the first example. The array is [2, 7, 11, 15]
  and the target is 9.
   - i = 0, num = 2, complement = 7, map is empty, so I add {2: 0}
   - i = 1, num = 7, complement = 2, and 2 is in the map at index 0
   - So I return [0, 1]. That matches the expected output."

Then state the complexity:
  "The time complexity is O(n) because we iterate through the array once.
  The space complexity is O(n) because the HashMap can store up to n elements."


---


## If the Interviewer Gives a Hint

Take it. Do not ignore hints. The interviewer is trying to help you, and
ignoring their suggestion makes a bad impression.

  "That is a great point. If I use [their suggestion], I can [explain how
  it improves the solution]..."


---


## If You Make a Mistake

Stay calm. Everyone makes mistakes.

  "Oh, I see the issue. I have an off-by-one error here because the loop
  should go up to nums.length - 1, not nums.length. Let me fix that."

Do not panic. Do not start over. Just identify the bug, fix it, and move on.


---


## Common Phrases That Sound Good in Interviews

  "Let me think about the time and space tradeoff here..."
  "I think sorting would help because it gives us a structure to work with..."
  "Can we assume the input fits in memory?"
  "I am going to handle the edge case of empty input first..."
  "The key insight here is that..."
  "This is similar to [well-known problem], so I think [approach] would work..."
  "If we had more time, I would optimize this by..."
