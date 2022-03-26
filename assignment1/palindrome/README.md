<!--NO_HARDWRAPS-->

# Instructions

For the following problem and its implementation, you have to (recommended: follow the order below):

1. Use **specification-based testing** techniques to derive tests for this method. In the documentation of the tests (e.g., javadoc) it must be clear what you considered as boundaries and which specific points you decided to test and why.
2. Use **structural testing** techniques to augment your test suite. You should achieve the highest possible (ideally, 100%) branch+condition coverage.

Automate the test cases as **JUnit5** test methods, by filling in the example test classes located in the folder `src/test/java/zest`.

You find **two implementations** of the problem's solution in the folder `src/main/java/zest`. Make sure to test them both.

The tests are going to be evaluated according to the points 1 and 2, also by using the tool [Andy](https://github.com/cse1110/andy). You can find examples of problems and solutions for this kind of tests (specification-based and structural), as well as see Andy in action, [here](https://github.com/cse1110/assignments/tree/main/domain-and-structural-testing).

---

# Problem: Palindrome Number
(Inspired by [LeetCode's Maximum Subarray problem](https://leetcode.com/problems/palindrome-number/))

## Description

Given an integer `x`, return `true` if `x` is palindrome integer.

An integer is a **palindrome** when it reads the same backward as forward.

For example, `121` is a palindrome while `123` is not.


### Example 1

**Input**: `x = 121`

**Output**: `true`

**Explanation**:
`121` reads as `121` from left to right and from right to left..


### Example 2

**Input**: `x = -121`

**Output**: `false `

**Explanation**:
From left to right, it reads `-121`. From right to left, it becomes `121-`. Therefore it is not a palindrome.

### Example 3

**Input**: `x = 10`

**Output**: `false `


### Constraints
-   `-2^20 <= x <= 2^20 - 1`

