package ca.tsmoreland.courseinfo.cli;

import ca.tsmoreland.courseinfo.cli.service.CourseRetrievalService;
import ca.tsmoreland.courseinfo.cli.service.CourseStorageService;
import ca.tsmoreland.courseinfo.cli.service.PluralsightCourse;
import ca.tsmoreland.courseinfo.repository.CourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static java.util.function.Predicate.not;

public class CourseRetriever {

    private static final Logger LOG = LoggerFactory.getLogger(CourseRetriever.class);

    public static void main(String... args) {
        if (args.length == 0) {
            LOG.warn("Please provide an author name.");
            return;
        }

        try {
            retrieveCourses(args[0]);
        } catch (Exception e) {
            LOG.error("Unexpected error", e);
        }
    }

    private static void retrieveCourses(String authorId) {
        LOG.info("Retrieving courses for '{}'", authorId);

        var storageService = new CourseStorageService(CourseRepository.openCourseRepositoryFromFile("./courses.db"));
        var retrievalService = new CourseRetrievalService();
        List<PluralsightCourse> courses = retrievalService.getCoursesFor(authorId).
            stream().
            filter(not(PluralsightCourse::isRetired)).
            toList();
        LOG.info("retrieved {} courses: {}", courses.size(), courses);
        storageService.storePluralsightCourses(courses);
        LOG.info("Courses successfully stored.");
    }
}
