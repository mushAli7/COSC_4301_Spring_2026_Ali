package model;

/*
 * Request body used when creating a new creature.
 */
public class CreateCreatureRequest {

    public String name;
    public String species;
    public String dangerLevel;
    public String status;
    public String notes;
    public Long habitatId;
}