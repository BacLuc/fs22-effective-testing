<!--NO_HARDWRAPS-->

# Instructions

For the following problem and its implementation, you have to (recommended: follow the order below):

1. Use **property-based testing** techniques to derive tests for this
method, using [jqwik](https://jqwik.net/) as a platform. In the documentation
of the tests (e.g., javadoc) and the naming of the methods/tests, it must be
clear what your rationale was behind your testing decision.
2. Use **structural testing** techniques to augment your test suite. You should achieve the highest possible (ideally, 100%) branch+condition coverage.

Automate the test cases as **JUnit5** test methods, by filling in the example test class located in the folder `src/test/java/zest`.

You find the implementation of the problem's solution in the folder `src/main/java/zest`.

---

# Problem: Jump Game

(Inspired by [LeetCode's Jump Game](https://leetcode.com/problems/jump-game))

## Description

You are given an integer array `nums`. You are initially positioned at the
array's *first index*, and each element in the array represents your maximum jump length at that position.

Return `true` if you can reach the last index, or `false` otherwise.

### Example 1

**Input**: `nums = [2,3,1,1,4]`

**Output**: `True`

**Explanation**:
Jump 1 step from index 0 to 1, then 3 steps to the last index.

### Example 2

**Input**: `nums = [3,2,1,0,4]`

**Output**: `False`

**Explanation**:
You will always arrive at index 3 no matter what. Its maximum jump length is 0,
which makes it impossible to reach the last index.


### Constraints
-   `1 <= nums.length <= 1000`
-   `0 <= nums[i] <= 10000`

