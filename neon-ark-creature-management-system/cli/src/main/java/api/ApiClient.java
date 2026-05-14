package api;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/*
 * Central HTTP utility used by the CLI application.
 *
 * Handles communication with the Spring Boot backend API.
 * Also provides reusable JSON serialization/deserialization.
 */
public class ApiClient {

    // Base URL for the backend API
    private static final String BASE_URL = "http://localhost:8081/api";

    // Shared HTTP client instance
    private final HttpClient httpClient;

    // Shared Jackson mapper for JSON conversion
    private final ObjectMapper objectMapper;

    /*
     * Initializes reusable HTTP and JSON utilities.
     */
    public ApiClient() {

        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    /*
     * Executes a GET request and returns the raw response body.
     */
    public String get(String endpoint)
            throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + endpoint))
                .GET()
                .build();

        HttpResponse<String> response =
                httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return handleResponse(response);
    }

    /*
     * Executes a POST request with a JSON body.
     */
    public String post(String endpoint, Object requestBody)
            throws IOException, InterruptedException {

        String jsonBody = objectMapper.writeValueAsString(requestBody);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + endpoint))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response =
                httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return handleResponse(response);
    }

    /*
     * Executes a PUT request with a JSON body.
     */
    public String put(String endpoint, Object requestBody)
            throws IOException, InterruptedException {

        String jsonBody = objectMapper.writeValueAsString(requestBody);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + endpoint))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response =
                httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return handleResponse(response);
    }

    /*
     * Executes a DELETE request.
     */
    public String delete(String endpoint)
            throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + endpoint))
                .DELETE()
                .build();

        HttpResponse<String> response =
                httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return handleResponse(response);
    }

    /*
     * Validates the HTTP response status code.
     */
    private String handleResponse(HttpResponse<String> response) {

        int statusCode = response.statusCode();

        if (statusCode >= 200 && statusCode < 300) {
            return response.body();
        }

        throw new ApiException(
                statusCode,
                response.body()
        );
    }

    /*
     * Returns the shared Jackson mapper instance.
     */
    public ObjectMapper getObjectMapper() {

        return objectMapper;
    }
}