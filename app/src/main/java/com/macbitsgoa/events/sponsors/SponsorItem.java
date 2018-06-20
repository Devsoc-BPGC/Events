package com.macbitsgoa.events.sponsors;

public class SponsorItem {
    private String sponsorName;
    private String sponsorLogo;
    private String sponsorClickUrl;

    public SponsorItem(final String sponsorName, final String sponsorLogo
            , final String sponsorClickUrl) {
        this.sponsorName = sponsorName;
        this.sponsorLogo = sponsorLogo;
        this.sponsorClickUrl = sponsorClickUrl;
    }

    public String getSponsorName() {
        return sponsorName;
    }

    public String getSponsorLogo() {
        return sponsorLogo;
    }

    public String getSponsorClickUrl() {
        return sponsorClickUrl;
    }
}
