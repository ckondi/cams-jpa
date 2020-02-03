package com.cams.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Getter
@Setter
public class ContactDetailsDTO {

    private String value;
    private String channelType;
    @Pattern(regexp = "^(?:Y|N)$", message = "Input should be either Y or N")
    private String primary;
    @Pattern(regexp = "^(?:Y|N)$", message = "Input should be either Y or N")
    private String verified;
    private String tag;

}
