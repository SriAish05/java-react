package com.cognizant.springlearn.model;

/**
 * Country model class - Represents a country with code and name.
 * This bean is loaded from the Spring XML configuration.
 */
public class Country {

    private String code;
    private String name;

    public Country() {
        // Required no-arg constructor for Spring bean instantiation
    }

    public Country(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return "Country [code=" + code + ", name=" + name + "]";
    }
}
