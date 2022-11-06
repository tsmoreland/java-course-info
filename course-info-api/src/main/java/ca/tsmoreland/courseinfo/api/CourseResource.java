package ca.tsmoreland.courseinfo.api;

import ca.tsmoreland.courseinfo.domain.PluralsightCourse;
import ca.tsmoreland.courseinfo.repository.CourseRepository;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Collectors;

@Path("/courses")
public class CourseResource {

    private static Logger LOG = LoggerFactory.getLogger(CourseResource.class);

    private final CourseRepository repository;

    public CourseResource(CourseRepository repository) {
        this.repository = repository;
    }

    @GET
    public String getCourses() {
        return repository.
            getAllCourses().
            stream().
            map(PluralsightCourse::toString).
            collect(Collectors.joining(","));
    }
}
