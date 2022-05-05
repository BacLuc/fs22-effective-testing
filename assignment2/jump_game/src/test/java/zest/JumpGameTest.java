package zest;

import net.jqwik.api.*;
import net.jqwik.api.arbitraries.IntegerArbitrary;
import net.jqwik.api.statistics.Histogram;
import net.jqwik.api.statistics.StatisticsReport;

import java.util.Arrays;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

class JumpGameTest {
    @Property
    public void array_is_jumpable_if_values_are_greater_than_1(@ForAll("jumpAbleArrays") Integer[] array) {
        int[] primitiveArray = Arrays.stream(array).mapToInt(value -> value).toArray();
        assertThat(new JumpGame(primitiveArray).canJump()).isTrue();
    }

    @SuppressWarnings("unused")
    @Provide
    Arbitrary<Integer[]> jumpAbleArrays() {
        IntegerArbitrary integers = Arbitraries.integers().between(1, (int) 1E4);
        return integers.array(Integer[].class).ofMinSize(1).ofMaxSize((int) 1E3);
    }

    @Property
    @StatisticsReport(format = Histogram.class)
    public void array_is_not_jumpable_if_it_contains_too_many_0(@ForAll("arraysWithTooMany0") Integer[] array) {
        int[] primitiveArray = Arrays.stream(array).mapToInt(value -> value).toArray();
        assertThat(new JumpGame(primitiveArray).canJump()).isFalse();
    }


    /**
     * I started with a distribution of 3 0s to 7 other numbers, but had to reduce it to this rather boring sample
     * definition that the test passes.
     */
    @SuppressWarnings("unused")
    @Provide
    Arbitrary<Integer[]> arraysWithTooMany0() {
        Arbitrary<Integer> integers = Arbitraries.frequency(
                Tuple.of(9, Arbitraries.just(0)),
                Tuple.of(1, Arbitraries.integers().between(1, 5))
        ).flatMap(Function.identity());
        return integers.array(Integer[].class).ofMinSize(20).ofMaxSize((int) 1E3);
    }
}
