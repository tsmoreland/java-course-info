package ca.tsmoreland.courseinfo.repository;

import ca.tsmoreland.courseinfo.domain.PluralsightCourse;

import java.util.List;

public interface CourseRepository {
    void addAndSaveCourse(PluralsightCourse course);
    void addOrUpdateNotes(String id, String notes);

    List<PluralsightCourse> getAllCourses();

    static CourseRepository openCourseRepositoryFromFile(String databaseFile) {
        return new JdbcCourseRepository(databaseFile);
    }
}
