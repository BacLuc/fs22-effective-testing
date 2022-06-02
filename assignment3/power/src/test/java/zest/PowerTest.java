package zest;

import net.jqwik.api.*;
import net.jqwik.api.lifecycle.BeforeProperty;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @Test
    void return_1_if_power_is_0() {
        assertThat(powerCalculator.myPow(5, 0)).isEqualTo(1);
        assertThat(powerCalculator.myPow(0, 0)).isEqualTo(1);
        assertThat(powerCalculator.myPow(1, 0)).isEqualTo(1);
    }

    @ParameterizedTest
    @CsvSource({
            "5.0, -2",
            "5.0, 2",
            "5.0, 4",
            "5.0, 5",
            "1.0, -2",
            "1.0, 2",
            "1.0, 4",
            "1.0, 5",
            "0.0, -2",
            "0.0, 2",
            "0.0, 4",
            "0.0, 5",
            "-1.0, -2",
            "-1.0, 2",
            "-1.0, 4",
            "-1.0, 5",
            "-5.0, -2",
            "-5.0, 2",
            "-5.0, 4",
            "-5.0, 5",
    })
    void calculate_correct_powers(double base, int power) {
        assertThat(powerCalculator.myPow(base, power)).isCloseTo(Math.pow(base, power), ERROR_MARGIN);
    }
}
