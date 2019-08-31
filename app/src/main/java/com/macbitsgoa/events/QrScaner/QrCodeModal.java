package com.macbitsgoa.events.QrScaner;

public class QrCodeModal {
    private String qrcode;
    private String descrip;
    private Long Points;

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public Long getPoints() {
        return Points;
    }

    public void setPoints(Long points) {
        Points = points;
    }

    public QrCodeModal(String qrcode, String descrip, Long points) {
        this.qrcode = qrcode;
        this.descrip = descrip;
        Points = points;
    }
}
