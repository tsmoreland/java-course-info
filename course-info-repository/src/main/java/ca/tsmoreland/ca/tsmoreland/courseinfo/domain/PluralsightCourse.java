package ca.tsmoreland.ca.tsmoreland.courseinfo.domain;

public record PluralsightCourse(String id, String name, long length, String url) {

    public PluralsightCourse {
        throwIfNullOrBlank(id);
        throwIfNullOrBlank(name);
        throwIfNullOrBlank(url);
    }

    private static void throwIfNullOrBlank(String s) {
        if (s == null || s.isBlank()) {
            throw new IllegalArgumentException("value cannot be null or blank");
        }
    }
}
