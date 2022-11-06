package ca.tsmoreland.courseinfo.cli.service;

import ca.tsmoreland.courseinfo.repository.CourseRepository;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

public class CourseStorageService {
    private static final String PS_URL = "https://app.pluralsight.com%s";
    private final CourseRepository repository;

    public CourseStorageService(CourseRepository repository) {
        this.repository = repository;
    }

    public void storePluralsightCourses(List<PluralsightCourse> courses) {
        for (PluralsightCourse course: courses) {
            var entity = toEntity(course);
            repository.addAndSaveCourse(entity);
        }
    }

    private ca.tsmoreland.courseinfo.domain.PluralsightCourse toEntity(PluralsightCourse source) {
        return new ca.tsmoreland.courseinfo.domain.PluralsightCourse(
            source.id(),
            source.title(),
            source.parseDuration().orElse(Duration.ZERO).toSeconds(),
            PS_URL.formatted(source.contentUrl()),
            Optional.empty()
        );
    }
}
