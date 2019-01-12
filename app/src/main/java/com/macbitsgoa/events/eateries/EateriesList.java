package com.macbitsgoa.events.eateries;

public class EateriesList {
    private String pic;
    private String name;
    private String desc;
    private String location;

    public EateriesList(String desc, String location, String name, String pic) {
        this.pic = pic;
        this.name = name;
        this.desc = desc;
        this.location = location;
    }

    public EateriesList(){}

    public String getDesc() {
        return desc;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getPic() {
        return pic;
    }
}
