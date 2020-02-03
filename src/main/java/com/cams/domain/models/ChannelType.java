package com.cams.domain.models;

public enum ChannelType {

    MOBILE, SOCIAL, EMAIL;

    public static ChannelType getFromValue(String value){
        return (null==value || value.length()==0) ? null : valueOf(value.toUpperCase());
    }

    public static String asString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0; i<values().length ; i++){
            stringBuilder.append(values()[i].name());
            stringBuilder.append(" ");
        }
        return stringBuilder.toString().trim();
    }
}
