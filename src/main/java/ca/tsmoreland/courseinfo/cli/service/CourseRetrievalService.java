package ca.tsmoreland.courseinfo.cli.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CourseRetrievalService {

    private static final String PLURALSIGHT_API_URI = "https://app.pluralsight.com/profile/data/author/%s/all-content";
    private static final HttpClient CLIENT = HttpClient.
        newBuilder().
        followRedirects(HttpClient.Redirect.ALWAYS).
        build();

    public String getCoursesFor(String authorId) {
        HttpRequest request = HttpRequest.
            newBuilder(URI.create(PLURALSIGHT_API_URI.formatted(authorId))).
            GET().
            build();

        try {
            HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

            return switch(response.statusCode()) {
                case 200 -> response.body();
                case 404 -> "";
                default -> throw new RuntimeException("API Call failed with " + response.statusCode());
            };
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error occurred calling API", e);
        }
    }
}
