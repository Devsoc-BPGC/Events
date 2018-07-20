package com.macbitsgoa.events.timeline;

public class Tabs {

    private int tabId;
    private String tabName;

    protected Tabs (int tabId, String tabName) {
        this.tabId = tabId;
        this.tabName = tabName;
    }

    public Tabs () {}

    public int getTabId () {
        return tabId;
    }

    public void setTabId (int tabId) {
        this.tabId = tabId;
    }

    public String getTabName () {
        return tabName;
    }

    public void setTabName (String tabName) {
        this.tabName = tabName;
    }
}