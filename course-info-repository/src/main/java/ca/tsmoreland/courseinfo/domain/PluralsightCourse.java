package ca.tsmoreland.courseinfo.domain;

import java.util.Optional;

public record PluralsightCourse(String id, String name, long length, String url, Optional<String> notes) {

    public PluralsightCourse {
        throwIfNullOrBlank(id);
        throwIfNullOrBlank(name);
        throwIfNullOrBlank(url);
        notes.ifPresent(PluralsightCourse::throwIfNullOrBlank);
    }

    private static void throwIfNullOrBlank(String s) {
        if (s == null || s.isBlank()) {
            throw new IllegalArgumentException("value cannot be null or blank");
        }
    }
}
