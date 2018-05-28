package com.macbitsgoa.events.feed;

/**
 * @author Aayush Singla
 */

class feedRecyclerViewItem {
    private String owner;
    private String ownerImage;
    private String imageUrl;
    private String desc;
    private String id;
    private int numberLikes;

    feedRecyclerViewItem() {

    }

    feedRecyclerViewItem(String id, String owner, String ownerImage, String imageUrl, int numberLikes, String desc) {
        this.id = id;
        this.owner = owner;
        this.imageUrl = imageUrl;
        this.ownerImage = ownerImage;
        this.numberLikes = numberLikes;
        this.desc = desc;
    }

    String getId() {
        return id;
    }

    String getOwner() {
        return owner;
    }

    String getOwnerImage() {
        return ownerImage;
    }

    String getImageUrl() {
        return imageUrl;
    }

    String getDesc() {
        return desc;
    }

    int getNumberLikes() {
        return numberLikes;
    }

}