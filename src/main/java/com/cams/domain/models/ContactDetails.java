package com.cams.domain.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@Embeddable
public class ContactDetails{


    private String value;
    @Enumerated(EnumType.STRING)
    @Column(name = "channel_type")
    private ChannelType channelType;
    @Column(name = "`primary`")
    private boolean primary;
    private boolean verified;
    private String tag;

    public ContactDetails() {
    }

    public ContactDetails(String value, ChannelType channelType, boolean primary, boolean verified, String tag) {
        this.value = value;
        this.channelType = channelType;
        this.primary = primary;
        this.verified = verified;
        this.tag = tag;
    }


}
