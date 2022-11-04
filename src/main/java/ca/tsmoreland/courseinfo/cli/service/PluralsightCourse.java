package ca.tsmoreland.courseinfo.cli.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PluralsightCourse(
    String id,
    String title,
    String duration,
    String contentUrl,
    String level,
    boolean isRetired) {

    public Optional<Duration> parseDuration() {
        try {

            return Optional.of(Duration.between(LocalTime.MIN, LocalTime.parse(duration())));
        } catch (Exception e) {

            return Optional.empty();
        }
    }
}
