package com.macbitsgoa.events.feed;

/**
 * Item for Feed Recycler View
 * @author Aayush Singla
 */

class FeedRecyclerViewItem {
    private String owner;
    private String ownerImage;
    private String imageUrl;
    private String desc;
    private String id;
    private int numberLikes;

    FeedRecyclerViewItem() {

    }

    FeedRecyclerViewItem(final String id, final String owner, final String ownerImage,
                         final String imageUrl, final int numberLikes, final String desc) {
        this.id = id;
        this.owner = owner;
        this.imageUrl = imageUrl;
        this.ownerImage = ownerImage;
        this.numberLikes = numberLikes;
        this.desc = desc;
    }

    public String getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public String getOwnerImage() {
        return ownerImage;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDesc() {
        return desc;
    }

    public int getNumberLikes() {
        return numberLikes;
    }

}