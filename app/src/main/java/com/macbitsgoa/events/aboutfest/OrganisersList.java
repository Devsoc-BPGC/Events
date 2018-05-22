package com.macbitsgoa.events.aboutfest;

public class OrganisersList {
    private String photo, name, contact, email, post;

    public OrganisersList(String photo, String name, String contact, String email, String post) {
        this.photo = photo;
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.post = post;
    }

    public OrganisersList() {}

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

    public String getPost() {
        return post;
    }
}
