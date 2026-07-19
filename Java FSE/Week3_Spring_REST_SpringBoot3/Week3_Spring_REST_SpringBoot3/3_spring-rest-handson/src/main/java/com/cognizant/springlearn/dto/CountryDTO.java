package com.cognizant.springlearn.dto;

/**
 * Data Transfer Object for Country.
 * Used to expose only necessary data to clients (decouples internal model from API).
 */
public class CountryDTO {

    private String code;
    private String name;
    private String displayName;

    public CountryDTO() {}

    public CountryDTO(String code, String name) {
        this.code = code;
        this.name = name;
        this.displayName = code + " - " + name;
    }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDisplayName() { return displayName; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }
}
