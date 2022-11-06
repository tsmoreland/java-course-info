package ca.tsmoreland.courseinfo.api;

import ca.tsmoreland.courseinfo.repository.CourseRepository;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

public class CourseServer {
    private static final Logger LOG = LoggerFactory.getLogger(CourseServer.class);
    private static final String BASE_URI = "http://localhost:8888";

    public static void main(String... args) {
        LOG.info("Starting server...");

        CourseRepository repository = CourseRepository.openCourseRepositoryFromFile("./courses.db");
        var config = new ResourceConfig().register(new CourseResource(repository));

        GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), config);
    }
}
