package com.cognizant.jwt.model;

/**
 * Response body for /authenticate endpoint.
 * Contains the generated JWT token that client must use for future requests.
 */
public class AuthResponse {

    private String token;
    private String username;
    private String type = "Bearer";

    public AuthResponse() {}

    public AuthResponse(String token, String username) {
        this.token = token;
        this.username = username;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}
