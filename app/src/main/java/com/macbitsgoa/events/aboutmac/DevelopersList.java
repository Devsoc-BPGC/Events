package com.macbitsgoa.events.aboutmac;

public class DevelopersList {
    private String photo;
    private String name;
    private String contact;
    private String email;

    /**
     * Constructor to make an organiser element.
     */
    public DevelopersList(String photo, String name, String contact, String email) {
        this.photo = photo;
        this.name = name;
        this.contact = contact;
        this.email = email;
    }

    public DevelopersList() {}

    public String getPhoto() {
        return photo;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public String getEmail() {
        return email;
    }

}
