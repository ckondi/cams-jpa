package com.cams.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

import static com.cams.util.UtilConstants.DOB_FORMAT;

@Getter
@Setter
public class CustomerDTO {

    @NotBlank
    @NotEmpty
    @NotNull
    private String firstName;
    @NotBlank
    @NotEmpty
    @NotNull
    private String lastName;
    private String gender;

    @Pattern(regexp = "^\\d{4}-(((0)[1-9])|((1)[0-2]))-([0-2][0-9]|(3)[0-1])$", message = "Invalid Date, pass in " + DOB_FORMAT + " format")
    private String dateOfBirth;
    @JsonProperty("contactDetails")
    @Valid
    private List<ContactDetailsDTO> contactDetailsDTOS;

}
