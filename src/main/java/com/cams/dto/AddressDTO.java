package com.cams.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class AddressDTO {

    @NotEmpty
    @NotBlank
    private String addressLine1;
    private String addressLine2;
    @NotEmpty
    @NotBlank
    private String city;
    @NotEmpty
    @NotBlank
    private String postcode;
    @Pattern(regexp = "^([a-z]|[A-Z]){2}$", message = "Input as code : like US, CA, UK etc")
    private String country;
    @Pattern(regexp = "^(?:Y|N)$", message = "Input should be either Y or N")
    private String active;

}
