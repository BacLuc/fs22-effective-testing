package zest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.List.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GenerateParenthesesTest {

  private GenerateParentheses generateParentheses;

  @BeforeEach
  public void setup() {
    generateParentheses = new GenerateParentheses();
  }

  @ParameterizedTest
  @MethodSource("inputs")
  void generates_parentheses(int input, List<String> expected) {
    assertThat(generateParentheses.generateParentheses(input)).hasSameElementsAs(expected);
  }

  public static Stream<Arguments> inputs() {
    return Stream.of(
        Arguments.of(1, of("()")),
        Arguments.of(2, of("(())", "()()")),
        Arguments.of(3, of("((()))", "(()())", "(())()", "()(())", "()()()")));
  }

  @ParameterizedTest
  @MethodSource("validInputRange")
  public void not_throw_exception_for_inputs_1_to_8(int input) {
    assertThat(generateParentheses.generateParentheses(input)).hasSizeGreaterThan(0);
  }

  static Stream<Arguments> validInputRange() {
    return IntStream.range(1, 9).mapToObj(Arguments::of);
  }

  @Test
  void generate_parentheses_for_input_8() {
    List<String> strings = generateParentheses.generateParentheses(8);
    assertThat(strings).hasSize(1430);
    assertTrue(strings.stream().anyMatch("(((((((())))))))"::equals));
    assertTrue(strings.stream().anyMatch("(((()))()()(()))"::equals));
    assertTrue(strings.stream().anyMatch("()((((()))())())"::equals));
    assertTrue(strings.stream().anyMatch("()()((()())()())"::equals));
    assertTrue(strings.stream().anyMatch("()()()()()()()()"::equals));
  }

  @Test
  void generate_parentheses_for_input_9() {
    List<String> strings = generateParentheses.generateParentheses(9);
    assertThat(strings).hasSize(4862);
  }

  @Test
  public void return_list_with_empty_string_for_0() {
    List<String> strings = generateParentheses.generateParentheses(0);
    assertThat(strings).hasSameElementsAs(of(""));
  }

  @Test
  public void throws_exception_for_negative_input() {
    assertThrows(
        NegativeArraySizeException.class, () -> generateParentheses.generateParentheses(-1));
  }
}
