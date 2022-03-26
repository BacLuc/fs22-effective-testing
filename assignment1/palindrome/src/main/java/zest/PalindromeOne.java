package zest;

/**
 * Solution inspired by: https://leetcode.com/problems/palindrome-number/discuss/429373/Easy-JAVA-solutions
 */
public class PalindromeOne {

    public boolean isPalindrome(int x) {
        // convert input into an array and rest is nothing but a simple two pointer solution
        char[] numbers = String.valueOf(x).toCharArray();
        int start = 0;
        int end = numbers.length - 1;
        while (start < end) {
            if (numbers[start] != numbers[end]) return false;
            start++;
            end--;
        }
        return true;
    }
}
