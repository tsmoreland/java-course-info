package ca.tsmoreland.courseinfo.cli.service;

import ca.tsmoreland.courseinfo.repository.CourseRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourseStorageServiceTest {

    @Test
    void storePluralsightCoursesShouldAddEachCourseToRepository() {
        CourseRepository repository = new InMemoryCourseRepository();
        var service = new CourseStorageService(repository);
        var course = new PluralsightCourse("05621eb6-f9b6-434a-97c6-bdd910a683d0", "Sample", "00:30:00", "/sample", "Intermediate", false);
        var expected = new ca.tsmoreland.courseinfo.domain.PluralsightCourse("05621eb6-f9b6-434a-97c6-bdd910a683d0", "Sample", 1800, "https://app.pluralsight.com/sample");

        service.storePluralsightCourses(List.of(course));

        assertEquals(List.of(expected), repository.getAllCourses());
    }

    static class InMemoryCourseRepository implements CourseRepository {

        private final List<ca.tsmoreland.courseinfo.domain.PluralsightCourse> courses = new ArrayList<>();

        @Override
        public void addAndSaveCourse(ca.tsmoreland.courseinfo.domain.PluralsightCourse course) {
            courses.add(course);
        }

        @Override
        public List<ca.tsmoreland.courseinfo.domain.PluralsightCourse> getAllCourses() {
            return Collections.unmodifiableList(courses);
        }
    }
}