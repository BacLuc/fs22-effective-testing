package zest;

import java.util.List;

public interface CourseCurriculum {

    public List<String> allCourses();

    public List<String> allTeachers();

    public void deleteCourse(String teacher, String courseTitle);

    public void addCourse(String teacher, String courseTitle, int ects);

    public String teacherCourse(String courseTitle);

    public int ectsCourse(String courseTitle);

    public int totalEcts();

}
