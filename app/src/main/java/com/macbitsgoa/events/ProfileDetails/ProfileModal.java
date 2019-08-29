package com.macbitsgoa.events.ProfileDetails;

public class ProfileModal {
    private String qrcode;
    private String Description;
    private Long Points;
    private int position;

    public ProfileModal(String qrcode, String description, Long points, int position) {
        this.qrcode = qrcode;
        Description = description;
        Points = points;
        this.position = position;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Long getPoints() {
        return Points;
    }

    public void setPoints(Long points) {
        Points = points;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }





    public String getQrcode(){
        return qrcode;
    }
}

