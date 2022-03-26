<!--NO_HARDWRAPS-->

# Instructions

For the following problem and its implementation, you have to (recommended: follow the order below):

1. Use **specification-based testing** techniques to derive tests for this method. In the documentation of the tests (e.g., javadoc) it must be clear what you considered as boundaries and which specific points you decided to test and why.
2. Use **structural testing** techniques to augment your test suite. You should achieve the highest possible (ideally, 100%) branch+condition coverage.

Automate the test cases as **JUnit5** test methods, by filling in the example test class located in the folder `src/test/java/zest`.

You find the implementation of the problem's solution in the folder `src/main/java/zest`.

The tests are going to be evaluated according to the points 1 and 2, also by using the tool [Andy](https://github.com/cse1110/andy). You can find examples of problems and solutions for this kind of tests (specification-based and structural), as well as see Andy in action, [here](https://github.com/cse1110/assignments/tree/main/domain-and-structural-testing).

---

# Problem: Combination Sum

(Inspired by [LeetCode's Combination Sum problem](https://leetcode.com/problems/combination-sum/))

## Description

Given an array of **distinct** integers `candidates` and a target integer `target`, return _a list of all **unique combinations** of_ `candidates` _where the chosen numbers sum to_ `target`. You may return the combinations in **any order**.

The **same** number may be chosen from `candidates` an **unlimited number of times**. Two combinations are unique if the frequency of at least one of the chosen numbers is different.

It is **guaranteed** that the number of unique combinations that sum up to `target` is less than `150` combinations for the given input.

### Example 1

**Input**: `candidates = [2,3,6,7]`, `target = 7`

**Output**: `[[2,2,3],[7]]`

**Explanation**:
`2` and `3` are candidates, and `2 + 2 + 3 = 7`.
Note that `2` can be used multiple times. `7` is a candidate, and `7` = `7`.
These are the only two combinations.


### Example 2

**Input**: `candidates = [2,3,5]`, `target = 8`

**Output**: `[[2,2,2,2],[2,3,3],[3,5]]`

### Example 3

**Input**: `candidates = [2]`, `target = 1`

**Output**: `[]`


### Constraints
-   `1 <= candidates.length <= 30`
-   `1 <= candidates[i] <= 200`
-   All elements of `candidates` are **distinct**.
-   `1 <= target <= 500`

