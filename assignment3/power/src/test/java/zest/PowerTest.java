package zest;

import net.jqwik.api.*;
import net.jqwik.api.lifecycle.BeforeProperty;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.BeforeEach;

import static org.assertj.core.api.Assertions.assertThat;

class PowerTest {

    private static final Percentage ERROR_MARGIN = Percentage.withPercentage(1);
    private Power powerCalculator;

    @BeforeEach
    @BeforeProperty
    void setup() {
        powerCalculator = new Power();
    }

    @Property
    void calculate_powers_correctly(@ForAll("bases") double base, @ForAll("powers") int power) {
        assertThat(powerCalculator.myPow(base, power)).isCloseTo(Math.pow(base, power), ERROR_MARGIN);
    }

    @Provide
    private Arbitrary<Double> bases() {
        return Arbitraries.doubles().between(-100, false, 100, false);
    }

    @Provide
    private Arbitrary<Integer> powers() {
        return Arbitraries.integers().between(-99, 99);
    }
}
