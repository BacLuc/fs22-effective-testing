package zest;

import org.apache.http.ParseException;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.notNull;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class CatFactsRetrieverTest {

    private static final String FACT = "fact";
    private static final String LONG_FACT = "long-fact";
    private static final String SHORT_FACT = "short-fact";
    private HttpUtil httpUtil;
    private CatFactsRetriever catFactsRetriever;

    @BeforeEach
    void setup() {
        httpUtil = mock(HttpUtil.class);
        catFactsRetriever = new CatFactsRetriever(httpUtil);
    }

    @Nested
    class RetrieveRandom {
        @Test
        public void retrieves_fact() throws IOException {
            when(httpUtil.get(notNull())).thenReturn(
                    """
                            {
                              "fact":"%s"
                            }
                            """.formatted(FACT));

            assertThat(catFactsRetriever.retrieveRandom()).isEqualTo(FACT);
        }

        @Test
        public void retrieves_empty_string_fact() throws IOException {
            when(httpUtil.get(notNull())).thenReturn(
                    """
                            {
                              "fact":""
                            }
                            """);

            assertThat(catFactsRetriever.retrieveRandom()).isEqualTo("");
        }
    }

    @Nested
    class RetrieveLongest {
        @Test
        public void throws_JSONException_if_response_does_not_contain_property_data() throws IOException {
            when(httpUtil.get(notNull())).thenReturn(
                    """
                            {
                              "fact":""
                            }
                            """
            );

            assertThrows(JSONException.class, () -> catFactsRetriever.retrieveLongest(1));
        }

        @Test
        public void throws_JSONException_if_data_is_not_array() throws IOException {
            when(httpUtil.get(notNull())).thenReturn(
                    """
                            {
                              "data":""
                            }
                            """
            );

            assertThrows(JSONException.class, () -> catFactsRetriever.retrieveLongest(1));
        }

        @Test
        public void returns_empty_string_for_empty_facts_list() throws IOException {
            when(httpUtil.get(notNull())).thenReturn(
                    """
                            {
                                "data": []
                            }
                            """);

            assertThat(catFactsRetriever.retrieveLongest(1)).isEqualTo("");
        }

        @Test
        public void throws_JSONException_if_length_not_present() throws IOException {
            when(httpUtil.get(notNull())).thenReturn(
                    """
                            {
                                "data": [
                                    {
                                        "fact": "%s"
                                    }
                                ]
                            }
                            """.formatted(FACT));

            assertThrows(JSONException.class, () -> catFactsRetriever.retrieveLongest(1));
        }

        @ParameterizedTest
        @ValueSource(ints = {-1, 0, 1, 2, 10, Integer.MAX_VALUE})
        public void use_length_parameter_in_url_for_HttpUtil(int limit) throws IOException {
            when(httpUtil.get(notNull())).thenReturn(
                    """
                            {
                                "data": [
                                    {
                                        "fact": "%s",
                                        "length": 1
                                    }
                                ]
                            }
                            """.formatted(FACT));

            assertThat(catFactsRetriever.retrieveLongest(limit)).isEqualTo(FACT);

            verify(httpUtil).get(CatFactsRetriever.CATFACTS_WITH_LIMIT + limit);
        }

        @Test
        public void returns_the_only_fact_if_only_one_fact() throws IOException {
            when(httpUtil.get(notNull())).thenReturn(
                    """
                            {
                                "data": [
                                    {
                                        "fact": "%s",
                                        "length": 1
                                    }
                                ]
                            }
                            """.formatted(FACT));

            assertThat(catFactsRetriever.retrieveLongest(1)).isEqualTo(FACT);
        }

        @Test
        public void returns_the_longest_fact_if_at_end() throws IOException {
            when(httpUtil.get(notNull())).thenReturn(
                    """
                            {
                                "data": [
                                    {
                                        "fact": "%s",
                                        "length": 1
                                    },
                                    {
                                        "fact": "%s",
                                        "length": 2
                                    },
                                    {
                                        "fact": "%s",
                                        "length": 3
                                    }
                                ]
                            }
                            """.formatted(SHORT_FACT, FACT, LONG_FACT));

            assertThat(catFactsRetriever.retrieveLongest(3)).isEqualTo(LONG_FACT);
        }

        @Test
        public void returns_the_longest_fact_if_at_start() throws IOException {
            when(httpUtil.get(notNull())).thenReturn(
                    """
                            {
                                "data": [
                                    {
                                        "fact": "%s",
                                        "length": 3
                                    },
                                    {
                                        "fact": "%s",
                                        "length": 2
                                    },
                                    {
                                        "fact": "%s",
                                        "length": 1
                                    }
                                ]
                            }
                            """.formatted(LONG_FACT, FACT, SHORT_FACT));

            assertThat(catFactsRetriever.retrieveLongest(3)).isEqualTo(LONG_FACT);
        }

        @Test
        public void takes_the_first_fact_if_2_facts_have_same_length() throws IOException {
            when(httpUtil.get(notNull())).thenReturn(
                    """
                            {
                                "data": [
                                    {
                                        "fact": "%s",
                                        "length": 3
                                    },
                                    {
                                        "fact": "%s",
                                        "length": 3
                                    },
                                    {
                                        "fact": "%s",
                                        "length": 1
                                    }
                                ]
                            }
                            """.formatted(LONG_FACT, FACT, SHORT_FACT));

            assertThat(catFactsRetriever.retrieveLongest(3)).isEqualTo(LONG_FACT);
        }

        @Test
        public void returns_the_longest_fact_if_in_the_middle() throws IOException {
            when(httpUtil.get(notNull())).thenReturn(
                    """
                            {
                                "data": [
                                    {
                                        "fact": "%s",
                                        "length": 2
                                    },
                                    {
                                        "fact": "%s",
                                        "length": 3
                                    },
                                    {
                                        "fact": "%s",
                                        "length": 1
                                    }
                                ]
                            }
                            """.formatted(FACT, LONG_FACT, SHORT_FACT));

            assertThat(catFactsRetriever.retrieveLongest(3)).isEqualTo(LONG_FACT);
        }

        @Test
        public void ignores_the_facts_string_length() throws IOException {
            when(httpUtil.get(notNull())).thenReturn(
                    """
                            {
                                "data": [
                                    {
                                        "fact": "%s",
                                        "length": 2
                                    },
                                    {
                                        "fact": "%s",
                                        "length": 3
                                    }
                                ]
                            }
                            """.formatted(LONG_FACT, SHORT_FACT));

            assertThat(catFactsRetriever.retrieveLongest(2)).isEqualTo(SHORT_FACT);
        }
    }

    @Test
    void throws_IOException_if_get_throws_ioexception() throws IOException {
        when(httpUtil.get(notNull())).thenThrow(IOException.class);

        assertThrows(IOException.class, () -> catFactsRetriever.retrieveRandom());
        assertThrows(IOException.class, () -> catFactsRetriever.retrieveLongest(1));
    }

    @Test
    public void throws_ParseException_if_response_is_not_parseable() throws IOException {
        when(httpUtil.get(notNull())).thenThrow(ParseException.class);

        assertThrows(ParseException.class, () -> catFactsRetriever.retrieveRandom());
        assertThrows(ParseException.class, () -> catFactsRetriever.retrieveLongest(1));
    }

    @ParameterizedTest
    @MethodSource("invalidResponses")
    public void throws_exception_for_invalid_response(String response, Class<? extends Exception> expectedException) throws IOException {
        when(httpUtil.get(notNull())).thenReturn(response);

        assertThrows(expectedException, () -> catFactsRetriever.retrieveRandom());
        assertThrows(expectedException, () -> catFactsRetriever.retrieveLongest(1));
    }

    @SuppressWarnings("unused")
    public static Stream<Arguments> invalidResponses() {
        return Stream.of(
                Arguments.of(null, NullPointerException.class),
                Arguments.of("", JSONException.class),
                Arguments.of("{", JSONException.class),
                Arguments.of("5", JSONException.class),
                Arguments.of("{}", JSONException.class),
                Arguments.of("""
                        {
                          "prop":null
                        }
                        """, JSONException.class),
                Arguments.of("""
                        {
                          "fact":null
                        }
                        """, JSONException.class),
                Arguments.of("""
                        {
                          "fact":5
                        }
                        """, JSONException.class),
                Arguments.of("""
                        {
                          "fact":true
                        }
                        """, JSONException.class)
        );
    }

}