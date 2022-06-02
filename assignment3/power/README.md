<!--NO_HARDWRAPS-->

# Instructions

For the following problem and its implementation, you have to (recommended: follow the order below):

1. Use **property-based testing** techniques to derive tests for this
method, using [jqwik](https://jqwik.net/) as a platform. In the documentation
of the tests (e.g., javadoc) and the naming of the methods/tests, it must be
clear what your rationale was behind your testing decision.
2. Use **structural testing** techniques to augment your test suite.
You should achieve the highest possible (ideally, 100%) branch+condition coverage.

Automate the test cases as **JUnit5** test methods, by filling in the example test class located in the folder `src/test/java/zest`.

You find the implementation of the problem's solution in the folder `src/main/java/zest`.
If the implementation does not handle the constraints correctly, write a new implementation `BetterPower`
that does it and test that instead.

---

# Problem: Power

(Inspired by [LeetCode's Pow(x,n)](https://leetcode.com/problems/powx-n/))

## Description

Implement `pow(x, n)`, which calculates `x` raised to the power `n`.

### Example 1

**Input**: `x = 2.00000, n = 10`

**Output**: `1024.00000`

### Example 2

**Input**: `x = 2.00000, n = -2`

**Output**: `0.25000`


### Constraints
- `-100.0 < x < 100.0`
- `-100.0 < n < 100.0`
- `-10^4 <= x^n <= 10^4`

