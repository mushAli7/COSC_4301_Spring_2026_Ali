package model;

// Represents a Neon Ark Warden record.
public class Warden {
    private int wardenId;
    private String firstName;
    private String lastName;
    private String email;
    private String identifierType;
    private String identifierValue;
    private String role;
    private String employmentStatus;
    private String clearanceLevel;
    private String startDate;
    private String endDate;

    public Warden() {
    }

    public Warden(int wardenId, String firstName, String lastName, String email,
                  String identifierType, String identifierValue, String role,
                  String employmentStatus, String clearanceLevel,
                  String startDate, String endDate) {
        this.wardenId = wardenId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.identifierType = identifierType;
        this.identifierValue = identifierValue;
        this.role = role;
        this.employmentStatus = employmentStatus;
        this.clearanceLevel = clearanceLevel;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getWardenId() {
        return wardenId;
    }

    public void setWardenId(int wardenId) {
        this.wardenId = wardenId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentifierType() {
        return identifierType;
    }

    public void setIdentifierType(String identifierType) {
        this.identifierType = identifierType;
    }

    public String getIdentifierValue() {
        return identifierValue;
    }

    public void setIdentifierValue(String identifierValue) {
        this.identifierValue = identifierValue;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public String getClearanceLevel() {
        return clearanceLevel;
    }

    public void setClearanceLevel(String clearanceLevel) {
        this.clearanceLevel = clearanceLevel;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return wardenId + " | " +
                firstName + " | " +
                lastName + " | " +
                email + " | " +
                identifierType + " | " +
                identifierValue + " | " +
                role + " | " +
                employmentStatus + " | " +
                clearanceLevel + " | " +
                startDate + " | " +
                endDate;
    }
}