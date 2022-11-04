package ca.tsmoreland.courseinfo.cli.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PluralsightCourse(
    String id,
    String title,
    String duration,
    String contentUrl,
    String level,
    boolean isRetired) {
}
