package ca.tsmoreland.courseinfo.repository;

import ca.tsmoreland.courseinfo.cli.service.PluralsightCourse;

import java.util.List;

public interface CourseRepository {
   void addAndSaveCourse(PluralsightCourse course);
   List<PluralsightCourse> getAllCourses();
}
