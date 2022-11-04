package ca.tsmoreland.courseinfo.cli.service;

import java.util.UUID;

public record PluralsightCourse(
    String id,
    String title,
    String duration,
    String contentUrl,
    String level,
    boolean isRetired) {
}
