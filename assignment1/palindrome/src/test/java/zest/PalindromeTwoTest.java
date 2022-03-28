package zest;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static zest.PalindromeTwo.isPalindrome;

class PalindromeTwoTest {
  @ParameterizedTest
  @CsvSource({
    "121, true",
    "123, false",
    "-121, false",
    "0, true",
    "9, true",
    "-9, false",
    "10, false",
    "11, true",
    "33, true",
    "99, true",
    "98, false",
    "100, false",
    "101, true",
    "110, false",
    "121, true",
    "999, true",
    "1000, false",
    "1001, true",
    "2332, true",
    "2331, false",
    "14541, true",
    "14542, false",
    "1234554321, true",
    "1234564321, false",
  })
  void return_correct_boolean_for_input(int input, boolean expected) {
    assertThat(isPalindrome(input)).isEqualTo(expected);
  }

  @ParameterizedTest
  @CsvSource({
    "-1048401, false",
    "-1058501, false",
    "1048401, true",
    "1048575, false",
    "1049401, true",
    "1049401, true",
    "-2147483648, false",
    "-2147447412, false",
    "2147483647, false",
    "2147447412, true",
  })
  void return_correct_boolean_for_input_at_boundaries(int input, boolean expected) {
    assertThat(isPalindrome(input)).isEqualTo(expected);
  }
}
