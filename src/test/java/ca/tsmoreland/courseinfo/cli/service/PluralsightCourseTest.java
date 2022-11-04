package ca.tsmoreland.courseinfo.cli.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Optional;

class PluralsightCourseTest {

    @Test
    void parseDuration() {

        var course = new PluralsightCourse("", "title", "00:30:00", "https://example.com", "Beginner", false);

        Optional<Duration> actual = course.parseDuration();
        Duration expected = Duration.ofMinutes(30);

        assertTrue(actual.isPresent());
        assertEquals(actual.get(), expected);
    }
}