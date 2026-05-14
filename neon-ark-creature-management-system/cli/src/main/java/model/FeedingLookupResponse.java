package model;

import java.util.List;

/*
 * Response body for feeding lookup results.
 */
public class FeedingLookupResponse {

    public String message;
    public List<FeedingResponse> feedings;
}