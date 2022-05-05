package zest;

import java.util.*;


class StatsAnalyzer {

    private final CourseCurriculum curriculum;

    public StatsAnalyzer(CourseCurriculum curriculum) {
        this.curriculum = curriculum;
    }

    /**
     * Compute the sum of all the ECTS of courses taught by a given teacher
     *
     * @param teacher name of the teacher
     * @return number of ECTS
     */
    public int getTeachersECTS(String teacher) {
        List<String> allCourses = curriculum.allCourses();
        int teachersECTS = 0;

        for (String courseTitle : allCourses) {
            if (teacher.equalsIgnoreCase(curriculum.teacherCourse(courseTitle))) {
                teachersECTS += curriculum.ectsCourse(courseTitle);
            }
        }

        return teachersECTS;
    }

    /**
     * Compute the average of all the ECTS of courses by teacher
     *
     * @return average ECTS by teacher
     */
    public int getAvgECTSByTeacher() {
        int totalECTS = curriculum.totalEcts();
        int totalTeachers = curriculum.allTeachers().size();

        return totalECTS / totalTeachers;
    }


}