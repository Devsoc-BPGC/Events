package com.macbitsgoa.events.speakers;

public class Speakers {

    public String image;
    public String name;
    public String desc;
    public String designation;
    public String onClick;

    public Speakers(final String image, final String name,final String desc,final String designation //StartedChanges
            , final String onClick) {
        this.image = image;
        this.desc = desc;
        this.name = name;
        this.designation=designation;
        this.onClick= onClick;
    }

    public String getImage() {
        return image;
    }

    public String getDesc() {
        return desc;
    }

    public String getDesignation() {
        return designation;
    }

    public String getName() {
        return name;
    }

    public String getOnClick() {
        return onClick;
    }  //EndedChanges
}