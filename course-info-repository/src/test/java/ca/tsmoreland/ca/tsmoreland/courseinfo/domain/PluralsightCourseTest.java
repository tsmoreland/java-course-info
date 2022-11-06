package ca.tsmoreland.ca.tsmoreland.courseinfo.domain;

import ca.tsmoreland.courseinfo.domain.PluralsightCourse;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PluralsightCourseTest {

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = { "", " ", "\t"})
    void constructorShouldThrowIllegalArgumentExceptionWhenIdIsNullOrBlank(String value) {
        assertThrows(IllegalArgumentException.class, () -> new PluralsightCourse(value, "name", 42, "/example", Optional.empty()));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = { "", " ", "\t"})
    void constructorShouldThrowIllegalArgumentExceptionWhenNameIsNullOrBlank(String value) {
        assertThrows(IllegalArgumentException.class, () -> new PluralsightCourse("id", value, 42, "/example", Optional.empty()));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = { "", " ", "\t"})
    void constructorShouldThrowIllegalArgumentExceptionWhenUrlIsNullOrBlank(String value) {
        assertThrows(IllegalArgumentException.class, () -> new PluralsightCourse("id", "name", 42, value, Optional.empty()));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = { "", " ", "\t"})
    void constructorShouldThrowIllegalArgumentExceptionWhenNotesIsNullOrBlank(String value) {
        assertThrows(IllegalArgumentException.class, () -> new PluralsightCourse("id", "name", 42, "/example", Optional.of(value)));
    }
}