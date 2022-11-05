package ca.tsmoreland.ca.tsmoreland.courseinfo.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PluralsightCourseTest {

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = { "", " ", "\t"})
    void constructorShouldThrowIllegalArgumentExceptionWhenIdIsNullOrBlank(String value) {
        assertThrows(IllegalArgumentException.class, () -> new PluralsightCourse(value, "name", 42, "https://example.com"));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = { "", " ", "\t"})
    void constructorShouldThrowIllegalArgumentExceptionWhenNameIsNullOrBlank(String value) {
        assertThrows(IllegalArgumentException.class, () -> new PluralsightCourse("id", value, 42, "https://example.com"));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = { "", " ", "\t"})
    void constructorShouldThrowIllegalArgumentExceptionWhenUrlIsNullOrBlank(String value) {
        assertThrows(IllegalArgumentException.class, () -> new PluralsightCourse("id", "name", 42, value));
    }

}