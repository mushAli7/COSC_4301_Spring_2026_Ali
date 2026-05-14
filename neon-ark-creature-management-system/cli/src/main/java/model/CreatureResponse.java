package model;

/*
 * Represents one creature record returned by the backend API.
 */
public class CreatureResponse {

    public Long creatureId;
    public String name;
    public String species;
    public String dangerLevel;
    public String status;
    public String habitatName;
    public String notes;
    public String createdAt;
}