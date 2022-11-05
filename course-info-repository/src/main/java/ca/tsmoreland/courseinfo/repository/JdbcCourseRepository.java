package ca.tsmoreland.courseinfo.repository;

import ca.tsmoreland.courseinfo.domain.PluralsightCourse;
import org.h2.jdbcx.JdbcDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class JdbcCourseRepository implements CourseRepository {
    private static final String H2_DATABASE_URL = "jdbc:h2:file:%s;AUTO_SERVER=TRUE;INIT=RUNSCRIPT FROM './db_init.sql'";
    private static final String INSERT_COURSE = """
        MERGE INTO Courses (id, name, length, url) 
        VALUES (?, ?, ?, ?)
        """;
    private final DataSource dataSource;

    public JdbcCourseRepository(String databaseFile) {
        var jdbcDataSource = new JdbcDataSource();
        jdbcDataSource.setURL(H2_DATABASE_URL.formatted(databaseFile));
        this.dataSource = jdbcDataSource;
    }

    @Override
    public void addAndSaveCourse(PluralsightCourse course) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INSERT_COURSE);
            statement.setString(1, course.id());
            statement.setString(2, course.name());
            statement.setLong(3, course.length());
            statement.setString(4, course.url());
            statement.execute();
        } catch (SQLException e) {
            throw new RepositoryException("Unable to save", e);
        }
    }

    @Override
    public List<PluralsightCourse> getAllCourses() {
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM COURSES");

            List<PluralsightCourse> courses = new ArrayList<>();
            while (results.next()) {
                var course = new PluralsightCourse(results.getString(1), results.getString(2), results.getLong(3), results.getString((4)));
                courses.add(course);
            }
            return Collections.unmodifiableList(courses);
        } catch (SQLException e) {
            throw new RepositoryException("Unable to save", e);
        }
    }
}
