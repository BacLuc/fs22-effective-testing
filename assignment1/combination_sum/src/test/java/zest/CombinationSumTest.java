package zest;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.List.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CombinationSumTest {

  private CombinationSum combinationSum;

  @BeforeEach
  public void setup() {
    combinationSum = new CombinationSum();
  }

  @ParameterizedTest
  @MethodSource("inputs")
  void finds_the_combination_sums(int[] array, int sum, List<List<Integer>> expected) {
    List<List<Integer>> result = combinationSum.combinationSum(array, sum);

    assertThat(result).hasSameElementsAs(expected);
    assertThat(result).hasSize(expected.size());
  }

  public static Stream<Arguments> inputs() {
    return Stream.of(
        Arguments.of(new int[] {}, 0, of(of())),
        Arguments.of(new int[] {}, 1, of()),
        Arguments.of(new int[] {1}, 1, of(of(1))),
        Arguments.of(new int[] {1, 2}, 1, of(of(1))),
        Arguments.of(new int[] {1, 2}, 3, of(of(1, 2), of(1, 1, 1))),
        Arguments.of(new int[] {1, 2, 2}, 3, of(of(1, 2), of(1, 2), of(1, 1, 1))),
        Arguments.of(new int[] {2, 2}, 3, of()),
        Arguments.of(
            new int[] {1, 2, 4}, 5, of(of(1, 1, 1, 1, 1), of(1, 1, 1, 2), of(1, 2, 2), of(1, 4))),
        Arguments.of(
            new int[] {4, 2, 1}, 5, of(of(1, 1, 1, 1, 1), of(1, 1, 1, 2), of(1, 2, 2), of(1, 4))),
        Arguments.of(new int[] {1}, 0, of(of())),
        Arguments.of(
            new int[] {100, 199, 200},
            500,
            of(of(100, 100, 100, 100, 100), of(100, 100, 100, 200), of(100, 200, 200))),
        Arguments.of(
            new int[] {100, 199, 200},
            599,
            of(of(100, 100, 100, 100, 199), of(100, 100, 199, 200), of(199, 200, 200))));
  }

  @ParameterizedTest
  @CsvSource(
      value = {"-1;-1", "-1,1;-1"},
      delimiter = ';')
  public void cannot_handle_negative_target(String array, int target) {
    Integer[] integers =
        Arrays.stream(array.split(",")).map(Integer::parseInt).toArray(Integer[]::new);
    int[] primitiveIntegers = ArrayUtils.toPrimitive(integers);

    List<List<Integer>> result = combinationSum.combinationSum(primitiveIntegers, target);
    assertThat(result).isEmpty();
  }

  @Test
  public void throws_StackoverFlowError_when_the_array_contains_minus_1() {
    assertThrows(
        StackOverflowError.class, () -> combinationSum.combinationSum(new int[] {-1, 1}, 1));
  }

  @ParameterizedTest
  @CsvSource(
      value = {"0,1;", "1,0", "1,0,1;"},
      delimiter = ';')
  public void throws_StackoverFlowError_when_the_array_contains_0(String input) {
    Integer[] integers =
        Arrays.stream(input.split(",")).map(Integer::parseInt).toArray(Integer[]::new);
    int[] primitiveIntegers = ArrayUtils.toPrimitive(integers);

    assertThrows(
        StackOverflowError.class, () -> combinationSum.combinationSum(primitiveIntegers, 1));
  }

  @Test
  public void calculates_combination_sums_up_to_30_items() {
    int arraySize = 30;
    int[] intArray = IntStream.range(1, arraySize + 1).toArray();

    List<List<Integer>> result = combinationSum.combinationSum(intArray, arraySize);
    assertThat(result).hasSize(5604);
    assertTrue(result.stream().anyMatch(integers -> of(15, 15).equals(integers)));
    assertTrue(result.stream().anyMatch(integers -> of(1, arraySize - 1).equals(integers)));
    assertTrue(result.stream().anyMatch(integers -> of(arraySize).equals(integers)));
  }

  @Test
  public void calculates_combination_sums_up_to_31_items() {
    int arraySize = 31;
    int[] intArray = IntStream.range(1, arraySize + 1).toArray();

    List<List<Integer>> result = combinationSum.combinationSum(intArray, arraySize);
    assertThat(result).hasSize(6842);
    assertTrue(result.stream().anyMatch(integers -> of(15, 16).equals(integers)));
    assertTrue(result.stream().anyMatch(integers -> of(1, arraySize - 1).equals(integers)));
    assertTrue(result.stream().anyMatch(integers -> of(arraySize).equals(integers)));
  }

  @Test
  public void throws_stackoverflowerror_for_very_large_array() {
    // as seen in java.util.stream.Nodes.MAX_ARRAY_SIZE
    int maxArraySize = Integer.MAX_VALUE - 8;
    int[] allPositiveInts = IntStream.range(1, maxArraySize).toArray();

    assertThrows(
        StackOverflowError.class,
        () -> combinationSum.combinationSum(allPositiveInts, Integer.MAX_VALUE));
  }
}
