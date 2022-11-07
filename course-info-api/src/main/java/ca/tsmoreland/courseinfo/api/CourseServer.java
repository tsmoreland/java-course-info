package ca.tsmoreland.courseinfo.api;

import ca.tsmoreland.courseinfo.repository.CourseRepository;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Properties;
import java.util.logging.LogManager;

public class CourseServer {
    private static final Logger LOG = LoggerFactory.getLogger(CourseServer.class);
    private static final String BASE_URI = "http://localhost:8888";

    static {
        LogManager.getLogManager().reset();
        org.slf4j.bridge.SLF4JBridgeHandler.install();
    }

    public static void main(String... args) {
        String databaseFilename = loadDatabaseFilename();
        LOG.info("Starting server...");

        CourseRepository repository = CourseRepository.openCourseRepositoryFromFile(databaseFilename);
        var config = new ResourceConfig().register(new CourseResource(repository));

        GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), config);
    }

    private static String loadDatabaseFilename() {
        var properties = new Properties();
        try (InputStream propertiesStream =  CourseServer.class.getResourceAsStream("./api.properties")) {
            properties.load(propertiesStream);
            return properties.getProperty("course-info.database");
        } catch (IOException e) {
            throw new IllegalStateException("Could not load database filename from properties", e);
        }
    }
}
