package com.macbitsgoa.events.profile;

public class Profile {
    private String qrcode;
    public Profile(){}

    public Profile(String Qrcode){
        qrcode=Qrcode;

    }


    public String getQrcode(){
        return qrcode;
    }
}
