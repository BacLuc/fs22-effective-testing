package zest;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CombinationSumTest {

    @Test
    void combinationSum() {
        CombinationSum cs = new CombinationSum();
        int[] intArray = new int[]{2,3,6,7};

        List<List<Integer>> results;
        results = cs.combinationSum(intArray,7);

        assertEquals(2, results.size());
    }
}