package zest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static zest.MaximumSubarray.maxSubArray;

class MaximumSubarrayTest {

  @ParameterizedTest
  @MethodSource("inputs")
  void return_largest_sum_of_sub_array(int[] array, int expectedSum) {
    assertThat(maxSubArray(array)).isEqualTo(expectedSum);
  }

  static Stream<Arguments> inputs() {
    return Stream.of(
        Arguments.of(new int[] {1}, 1),
        Arguments.of(new int[] {-1}, -1),
        Arguments.of(new int[] {5, 4, -1, 7, 8}, 23),
        Arguments.of(new int[] {5, 4, -1, -1, 7, 8}, 22),
        Arguments.of(new int[] {5, 4, 7, 8, -1}, 24),
        Arguments.of(new int[] {-1, -1, -1, -1, -1, -1}, -1),
        Arguments.of(new int[] {-5, -4}, -4),
        Arguments.of(new int[] {-5, -4, 1, -1, 1, 10}, 11),
        Arguments.of(new int[] {toInt(-1E4), toInt(1E4)}, toInt(1E4)));
  }

  @Test
  public void return_largest_sum_of_subarray_outside_boundaries() {
    assertThat(maxSubArray(new int[] {toInt(-1E4), toInt(-1E4) - 1})).isEqualTo(toInt(-1E4));
    assertThat(maxSubArray(new int[] {toInt(1E4), toInt(1E4) + 1})).isEqualTo(toInt(2E4) + 1);
  }

  @Test
  public void return_largest_sum_for_array_length_in_boundary() {
    int[] rangeInBoundaries = IntStream.range(1, toInt(1E3) + 1).toArray();
    assertThat(maxSubArray(rangeInBoundaries)).isEqualTo(500500);
  }

  @Test
  public void return_largest_sum_for_array_length_outside_boundary() {
    int[] rangeInBoundaries = IntStream.range(1, toInt(1E3) + 2).toArray();
    assertThat(maxSubArray(rangeInBoundaries)).isEqualTo(501501);
  }

  @Test
  public void throw_exception_for_array_length_0() {
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> maxSubArray(new int[] {}));
  }

  private static int toInt(double a) {
    return (int) Math.round(a);
  }
}
