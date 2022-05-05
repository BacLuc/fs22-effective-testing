package zest;

/**
 * Solution inspired by: https://leetcode.com/problems/jump-game/discuss/20923/Java-Solution-easy-to-understand
 */
public class JumpGame {

    private int[] array;

    public JumpGame(int[] array) {
        // check constraints
        if (array.length < 1 || array.length > 1000) throw new RuntimeException("Array size must be 1 <= size <= 1000");
        // check constraints
        for (int i : array)
            if (i < 0 || i > 10000) throw new RuntimeException("Each value in the array must be 0 <= value <= 10000");

        this.array = array;
    }

    public boolean canJump() {
        int max = 0;
        for (int i = 0; i < array.length; i++) {
            if (i > max) return false;

            max = Math.max(array[i] + i, max);
        }
        return true;
    }

}
