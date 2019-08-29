package com.macbitsgoa.events.DosmEvent;

import com.macbitsgoa.events.QrScaner.QrCodeModal;

import java.util.ArrayList;

public class UserDetailsModal {
    public String name;
     public Double points;
     public ArrayList<QrCodeModal> qrcodesScanned= new ArrayList<>();

//    public UserDetailsModal(String UID, String name, Long points, ArrayList<QrCodeModal> qrcodesScanned) {
//        this.UID = UID;
//        this.name = name;
//        this.points = points;
//        this.qrcodesScanned = qrcodesScanned;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPoints() {
        return points;
    }

    public void setPoints(Double points) {
        this.points = points;
    }

    public ArrayList<QrCodeModal> getQrcodesScanned() {
        return qrcodesScanned;
    }

    public void setQrcodesScanned(ArrayList<QrCodeModal> qrcodesScanned) {
        this.qrcodesScanned = qrcodesScanned;
    }
}
