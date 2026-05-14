package api;

import com.fasterxml.jackson.core.type.TypeReference;
import model.CreatureResponse;
import model.CreateCreatureRequest;
import model.RenameCreatureRequest;
import model.RenameCreatureResponse;
import model.MessageResponse;
import model.CreatureObservationResponse;
import model.FeedingLookupResponse;
import model.AdminUserResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

/*
 * API layer for creature-related backend requests.
 *
 * The menu should call this class instead of building
 * HTTP requests directly.
 */
public class CreatureApi {

    private final ApiClient apiClient;

    public CreatureApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /*
     * Calls GET /api/creatures.
     */
    public List<CreatureResponse> listAllCreatures()
            throws IOException, InterruptedException {

        String responseBody = apiClient.get("/creatures");

        return apiClient.getObjectMapper().readValue(
                responseBody,
                new TypeReference<List<CreatureResponse>>() {}
        );
    }

    /*
     * Calls GET /api/creatures/{id}.
     */
    public CreatureResponse getCreatureById(Long creatureId)
            throws IOException, InterruptedException {

        String responseBody = apiClient.get("/creatures/" + creatureId);

        return apiClient.getObjectMapper().readValue(
                responseBody,
                CreatureResponse.class
        );
    }

    /*
     * Calls POST /api/creatures.
     */
    public CreatureResponse createCreature(CreateCreatureRequest request)
            throws IOException, InterruptedException {

        String responseBody =
                apiClient.post("/creatures", request);

        return apiClient.getObjectMapper().readValue(
                responseBody,
                CreatureResponse.class
        );
    }

    /*
     * Calls PUT /api/creatures/{id}/name.
     */
    public RenameCreatureResponse renameCreature(
            Long creatureId,
            RenameCreatureRequest request)
            throws IOException, InterruptedException {

        String responseBody = apiClient.put(
                "/creatures/" + creatureId + "/name",
                request
        );

        return apiClient.getObjectMapper().readValue(
                responseBody,
                RenameCreatureResponse.class
        );
    }

    /*
     * Calls DELETE /api/creatures/{id}.
     */
    public MessageResponse removeCreature(Long creatureId)
            throws IOException, InterruptedException {

        String responseBody =
                apiClient.delete("/creatures/" + creatureId);

        return apiClient.getObjectMapper().readValue(
                responseBody,
                MessageResponse.class
        );
    }

    /*
     * Calls GET /api/creatures/{id}/observations.
     */
    public CreatureObservationResponse getCreatureObservations(Long creatureId)
            throws IOException, InterruptedException {

        String responseBody =
                apiClient.get("/creatures/" + creatureId + "/observations");

        return apiClient.getObjectMapper().readValue(
                responseBody,
                CreatureObservationResponse.class
        );
    }

    /*
     * Calls GET /api/feedings?time={HH:MM}.
     */
    public FeedingLookupResponse findFeedingsByTime(String time)
            throws IOException, InterruptedException {

        String responseBody =
                apiClient.get("/feedings?time=" + time);

        return apiClient.getObjectMapper().readValue(
                responseBody,
                FeedingLookupResponse.class
        );
    }

    /*
     * Calls GET /api/admin/users.
     */
    public List<AdminUserResponse> getAllUsers()
            throws IOException, InterruptedException {

        String responseBody =
                apiClient.get("/admin/users");

        return apiClient.getObjectMapper().readValue(
                responseBody,
                new TypeReference<List<AdminUserResponse>>() {}
        );
    }

    /*
     * Returns the shared object mapper.
     */
    public ObjectMapper getObjectMapper() {

        return apiClient.getObjectMapper();
    }
}