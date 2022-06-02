package zest;

/**
 * Solution inspired by: https://leetcode.com/problems/powx-n/discuss/336569/Java-solution
 */
public class BetterPower {
    public double myPow(double x, int n) {
        if (x <= -100.0 || x >= 100.0) {
            throw new IllegalArgumentException("x must be between -100 and 100");
        }

        if (n <= -100 || n >= 100) {
            throw new IllegalArgumentException("n must be between -100 and 100");
        }

        double result = calculatePower(x, n);
        if (Math.abs(result) > 1E4) {
            throw new RuntimeException("Result was too high, was: " + result);
        }
        return result;
    }

    /**
     * In intermediate steps, base and power may be outside the boundaries
     */
    private double calculatePower(double base, int power) {
        if(power <0) return 1 / base * calculatePower(1 / base, -(power + 1));
        if(power ==0) return 1;
        if(power ==2) return base * base;
        if(power %2==0) return calculatePower(calculatePower(base, power / 2), 2);
        else return base * calculatePower(calculatePower(base, power / 2), 2);
    }

}
