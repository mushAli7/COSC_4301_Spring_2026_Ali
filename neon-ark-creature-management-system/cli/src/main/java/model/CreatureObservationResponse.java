package model;

import java.util.List;

/*
 * Combined response containing creature details
 * and all related observations.
 */
public class CreatureObservationResponse {

    public CreatureResponse creature;
    public List<ObservationResponse> observations;
}