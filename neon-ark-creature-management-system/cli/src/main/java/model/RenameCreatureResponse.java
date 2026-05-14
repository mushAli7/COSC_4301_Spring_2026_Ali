package model;

/*
 * Response body returned after a creature rename operation.
 */
public class RenameCreatureResponse {

    public Long creatureId;
    public String oldName;
    public String newName;
    public String message;
}