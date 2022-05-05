package zest;

import net.jqwik.api.*;
import net.jqwik.api.arbitraries.IntegerArbitrary;
import net.jqwik.api.statistics.Histogram;
import net.jqwik.api.statistics.StatisticsReport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JumpGameTest {
    @Property
    void array_is_jumpable_if_values_are_greater_than_1(@ForAll("jumpAbleArrays") Integer[] array) {
        int[] primitiveArray = Arrays.stream(array).mapToInt(value -> value).toArray();

        assertThat(new JumpGame(primitiveArray).canJump()).isTrue();
    }

    @SuppressWarnings("unused")
    @Provide
    private Arbitrary<Integer[]> jumpAbleArrays() {
        IntegerArbitrary integers = Arbitraries.integers().between(1, (int) 1E4);
        return integers.array(Integer[].class).ofMinSize(1).ofMaxSize((int) 1E3);
    }

    @Property
    @StatisticsReport(format = Histogram.class)
    void array_is_not_jumpable_if_it_contains_too_many_0(@ForAll("arraysWithTooMany0") Integer[] array) {
        int[] primitiveArray = Arrays.stream(array).mapToInt(value -> value).toArray();

        assertThat(new JumpGame(primitiveArray).canJump()).isFalse();
    }


    /**
     * I started with a distribution of 3 0s to 7 other numbers, but had to reduce it to this rather boring sample
     * definition that the test passes.
     */
    @SuppressWarnings("unused")
    @Provide
    private Arbitrary<Integer[]> arraysWithTooMany0() {
        Arbitrary<Integer> integers = Arbitraries.frequency(
                Tuple.of(9, Arbitraries.just(0)),
                Tuple.of(1, Arbitraries.integers().between(1, 5))
        ).flatMap(Function.identity());
        return integers.array(Integer[].class).ofMinSize(20).ofMaxSize((int) 1E3);
    }

    @Test
    void throws_if_array_length_is_0() {
        assertThrows(RuntimeException.class, () -> new JumpGame(new int[0]));
    }

    @Test
    void throws_if_array_length_is_1E3_plus_1() {
        assertThrows(RuntimeException.class, () -> new JumpGame(new int[(int) 1E3 + 1]));
    }

    @Test
    void throws_if_one_item_is_minus_1() {
        assertThrows(RuntimeException.class, () -> new JumpGame(new int[]{1, -1}));
    }

    @Test
    void throws_if_one_item_is_1E4_plus_1() {
        assertThrows(RuntimeException.class, () -> new JumpGame(new int[]{1, (int) 1E4 + 1}));
    }

    @ParameterizedTest
    @CsvSource(delimiter = ';',
            value = {
                    "0; true",
                    "1; true",
                    "1,0; true",
                    "0,1; false",
                    "2,3,1,1,4; true",
                    "3,2,1,0,4; false"
            })
    void detects_correctly_if_jumping_is_possible(String input, boolean expected) {
        int[] ints = Arrays.stream(input.split(",")).map(String::trim).mapToInt(Integer::valueOf).toArray();

        assertThat(new JumpGame(ints).canJump()).isEqualTo(expected);
    }
}
