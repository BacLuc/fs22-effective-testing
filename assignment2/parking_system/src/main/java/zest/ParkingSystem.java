package zest;

import java.util.Arrays;

/**
 * Solution inspired by: https://leetcode.com/problems/design-parking-system/discuss/910746/Simple-java-solution-with-explaination-o(1)-time-o(1)-space-99.74-faster
 */
class ParkingSystem {
    private final int[] size;
    private int calls;

    public ParkingSystem(int big, int medium, int small) {
        // check constraints
        if (big < 0 || medium < 0 || small < 0)
            throw new RuntimeException("big, medium, and small availability must be at least 0");
        // check constraints
        if (big > 1000 || medium > 1000 || small > 1000)
            throw new RuntimeException("big, medium, and small availability must be at most 1000");

        this.size = new int[]{big, medium, small};
        this.calls = 0;
    }

    public boolean addCar(int carType) {
        if (this.calls >= 1000)
            throw new RuntimeException("At most 1000 calls can be made to this method");
        if (carType < 1 || carType > 3)
            throw new RuntimeException("Only car types 1,2,3 are available");

        this.calls += 1;
        return size[carType - 1]-- > 0;
    }
}
