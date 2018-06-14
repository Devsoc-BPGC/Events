package com.macbitsgoa.events.aboutfest;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Model for all data about fest.
 * @author Rushikesh Jogdand.
 */
public class AboutFest {
    public String description;

    @SerializedName("logourl")
    public String logoUrl;
    public List<Organiser> organisers;
}
