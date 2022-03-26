<!--NO_HARDWRAPS-->

# Instructions

For the following problem and its implementation, you have to (recommended: follow the order below):

1. Use **specification-based testing** techniques to derive tests for this method. In the documentation of the tests (e.g., javadoc) it must be clear what you considered as boundaries and which specific points you decided to test and why.
2. Use **structural testing** techniques to augment your test suite. You should achieve the highest possible (ideally, 100%) branch+condition coverage.

Automate the test cases as **JUnit5** test methods, by filling in the example test class located in the folder `src/test/java/zest`.

You find the implementation of the problem's solution in the folder `src/main/java/zest`.

The tests are going to be evaluated according to the points 1 and 2, also by using the tool [Andy](https://github.com/cse1110/andy). You can find examples of problems and solutions for this kind of tests (specification-based and structural), as well as see Andy in action, [here](https://github.com/cse1110/assignments/tree/main/domain-and-structural-testing).

---

# Problem: Maximum Subarray
(Inspired by [LeetCode's Maximum Subarray problem](https://leetcode.com/problems/maximum-subarray/))

## Description

Given an integer array `nums`, find the contiguous subarray (containing at least one number) which has the largest sum and return *its* sum.

A **subarray** is a **contiguous** part of an array.


### Example 1

**Input**: `nums = [-2,1,-3,4,-1,2,1,-5,4]`

**Output**: `6`

**Explanation**:
`[4,-1,2,1]` has the largest `sum = 6`.


### Example 2

**Input**: `nums = [1]`

**Output**: `1`

### Example 3

**Input**: `nums = [5,4,-1,7,8]`

**Output**: `23`


### Constraints
-   `1 <= nums.length <= 10^3`
-   `-10^4 <= nums[i] <= 10^4`

