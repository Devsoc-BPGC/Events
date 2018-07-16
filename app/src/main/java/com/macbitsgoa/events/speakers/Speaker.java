package com.macbitsgoa.events.speakers;

public class Speaker {
    String Name;
    String Desc;
    String imgURL;
    String clickURL;

    public Speaker() {
        Name = "";
        Desc = "";
        imgURL = "";
        clickURL = "";
    }

    public Speaker(String name, String desc, String imgURL, String clickURL) {
        Name = name;
        Desc = desc;
        this.imgURL = imgURL;
        this.clickURL = clickURL;
    }
}
