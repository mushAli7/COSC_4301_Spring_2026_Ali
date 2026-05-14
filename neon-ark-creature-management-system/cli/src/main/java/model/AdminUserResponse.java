package model;

/*
 * Represents one system user returned by the admin API route.
 */
public class AdminUserResponse {

    public Long userId;
    public String fullName;
    public String email;
    public String phone;
    public String username;
    public String role;
}