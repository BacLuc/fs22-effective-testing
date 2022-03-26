<!--NO_HARDWRAPS-->

# Instructions

For the following problem and its implementation, you have to (recommended: follow the order below):

1. Use **specification-based testing** techniques to derive tests for this method. In the documentation of the tests (e.g., javadoc) it must be clear what you considered as boundaries and which specific points you decided to test and why.
2. Use **structural testing** techniques to augment your test suite. You should achieve the highest possible (ideally, 100%) branch+condition coverage.

Automate the test cases as **JUnit5** test methods, by filling in the example test class located in the folder `src/test/java/zest`.

You find the implementation of the problem's solution in the folder `src/main/java/zest`.

The tests are going to be evaluated according to the points 1 and 2, also by using the tool [Andy](https://github.com/cse1110/andy). You can find examples of problems and solutions for this kind of tests (specification-based and structural), as well as see Andy in action, [here](https://github.com/cse1110/assignments/tree/main/domain-and-structural-testing).

---

# Problem: Generate Parentheses
(Inspired by [LeetCode's Generate Parentheses problem](https://leetcode.com/problems/generate-parentheses/))

## Description

Given `n` pairs of parentheses, write a function to generate all combinations of well-formed parentheses.


### Example 1

**Input**: `n = 3`

**Output**: `["((()))","(()())","(())()","()(())","()()()"]`


### Example 2

**Input**: `n = 1`

**Output**: `["()"] `


### Constraints
-   `1 <= n <= 8`

