package com.macbitsgoa.events.Nights;

public class Nights {

    public String Uid1;
    public String name;
    public String desc;
    public String imageURL;
    public String time;
    public String location;

    public Nights(final String Uid1,final String name,final String desc, final String imageURL
            ,final String time,final String location)
    {
        this.desc = desc;
        this.Uid1=Uid1;
        this.name = name;
        this.imageURL = imageURL;
        this.time=time;
        this.location=location;

    }

    public String getUid1() {
        return Uid1;
    }

    public String getDesc() {
        return desc;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String getLocation(){
        return  location;
    }

}
