package zest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.notNull;
import static org.assertj.core.api.Assertions.assertThat;

class StatsAnalyzerTest {

    private static final String COURSE_1 = "course1";
    private static final String COURSE_2 = "COURSE_2";
    private static final String TEACHER_1 = "teacher1";
    private CourseCurriculum courseCurriculum;
    private StatsAnalyzer statsAnalyzer;

    @BeforeEach
    public void setup() {
        courseCurriculum = mock(CourseCurriculum.class);
        statsAnalyzer = new StatsAnalyzer(courseCurriculum);
    }

    @Nested
    class GetAvgECTSByTeacher {
        @Test
        void throws_ArithmeticException_when_no_teachers() {
            when(courseCurriculum.totalEcts()).thenReturn(42);
            when(courseCurriculum.allTeachers()).thenReturn(List.of());

            assertThrows(ArithmeticException.class, () -> statsAnalyzer.getAvgECTSByTeacher());
        }

        @ParameterizedTest
        @CsvSource({
                "0, 0",
                "1, 0",
                "2, 1",
                "-1, 0",
                "-2, -1",
                "3, 1",
                "4, 2"
        })
        void return_avg_ECTS_by_teacher(int ects, int average) {
            when(courseCurriculum.totalEcts()).thenReturn(ects);
            when(courseCurriculum.allTeachers()).thenReturn(List.of("", ""));

            assertThat(statsAnalyzer.getAvgECTSByTeacher()).isEqualTo(average);
        }
    }

    @Nested
    class GetTeacherECTS {
        @Test
        void return_0_if_no_courses() {
            assertThat(statsAnalyzer.getTeachersECTS("")).isEqualTo(0);
        }

        @Test
        void throws_npe_if_one_course_exists_but_teacher_is_null() {
            when(courseCurriculum.allCourses()).thenReturn(List.of(""));

            assertThrows(NullPointerException.class, () -> statsAnalyzer.getTeachersECTS(null));
        }

        @Test
        void return_0_if_teacher_has_no_courses() {
            when(courseCurriculum.allCourses()).thenReturn(List.of(""));

            assertThat(statsAnalyzer.getTeachersECTS("")).isEqualTo(0);
        }

        @Test
        void return_sum_of_etcs_for_teacher() {
            when(courseCurriculum.allCourses()).thenReturn(List.of(COURSE_1, COURSE_2));
            when(courseCurriculum.teacherCourse(notNull())).thenReturn(TEACHER_1);
            when(courseCurriculum.ectsCourse(notNull())).thenReturn(1);

            assertThat(statsAnalyzer.getTeachersECTS(TEACHER_1)).isEqualTo(2);
        }

        @Test
        void only_sum_up_ects_of_courses_the_teacher_gives() {
            when(courseCurriculum.allCourses()).thenReturn(List.of(COURSE_1, COURSE_2));
            when(courseCurriculum.teacherCourse(COURSE_1)).thenReturn(TEACHER_1);
            when(courseCurriculum.ectsCourse(notNull())).thenReturn(1);

            assertThat(statsAnalyzer.getTeachersECTS(TEACHER_1)).isEqualTo(1);
        }

        @Test
        void ignore_the_case_of_the_teacher() {
            when(courseCurriculum.allCourses()).thenReturn(List.of(COURSE_1, COURSE_2));
            when(courseCurriculum.teacherCourse(COURSE_1)).thenReturn(TEACHER_1);
            when(courseCurriculum.ectsCourse(notNull())).thenReturn(1);

            assertThat(statsAnalyzer.getTeachersECTS(TEACHER_1.toLowerCase())).isEqualTo(1);
        }
    }
}