package com.company.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
@NotNull(message="Street field can not be empty")
    private String street;
@NotNull(message="Home number can not be empty")
    private Integer homeNumber;
}
