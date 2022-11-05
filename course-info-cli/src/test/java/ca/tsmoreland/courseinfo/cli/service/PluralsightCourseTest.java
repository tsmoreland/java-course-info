package ca.tsmoreland.courseinfo.cli.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.Duration;
import java.util.Optional;

class PluralsightCourseTest {

    @ParameterizedTest
    @CsvSource(textBlock = """
        00:30:00, 1800
        00:00:00, 0
        """)
    void parseDurationShouldReturnDurationWhenDurationStringIsValid(String sourceDuration, long expectedSeconds) {

        var course = new PluralsightCourse("4fc31c8b-97c7-4bb9-a8be-639d8992146b", "title", sourceDuration, "https://example.com", "Beginner", false);

        Optional<Duration> actual = course.parseDuration();
        Duration expected = Duration.ofSeconds(expectedSeconds);

        assertTrue(actual.isPresent());
        assertEquals(actual.get(), expected);
    }
}