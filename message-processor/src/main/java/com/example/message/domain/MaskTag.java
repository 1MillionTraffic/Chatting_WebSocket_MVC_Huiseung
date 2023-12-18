package com.example.message.domain;

public enum MaskTag {
    ABUSE_START_TAG("<ab>"),
    ABUSE_END_TAG("</ab>"),
    ;

    private final String tag;

    MaskTag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }
}
