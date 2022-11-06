package ca.tsmoreland.courseinfo.api;

import ca.tsmoreland.courseinfo.domain.PluralsightCourse;
import ca.tsmoreland.courseinfo.repository.CourseRepository;
import ca.tsmoreland.courseinfo.repository.RepositoryException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;

@Path("/courses")
public class CourseResource {

    private static final Logger LOG = LoggerFactory.getLogger(CourseResource.class);

    private final CourseRepository repository;

    public CourseResource(CourseRepository repository) {
        this.repository = repository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PluralsightCourse> getCourses() {
        try {
            return repository.
                getAllCourses().
                stream().
                sorted(Comparator.comparing(PluralsightCourse::name)).
                toList();
        } catch (RepositoryException e) {
            LOG.error("An error occurred retrieving courses ", e);
            throw new ServiceUnavailableException("Unexpected error occurred.");
        }
    }

}
