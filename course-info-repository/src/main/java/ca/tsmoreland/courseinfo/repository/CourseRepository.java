package ca.tsmoreland.courseinfo.repository;

import ca.tsmoreland.courseinfo.domain.PluralsightCourse;

import java.util.List;

public interface CourseRepository {
    void addAndSaveCourse(PluralsightCourse course);
    List<PluralsightCourse> getAllCourses();
}
