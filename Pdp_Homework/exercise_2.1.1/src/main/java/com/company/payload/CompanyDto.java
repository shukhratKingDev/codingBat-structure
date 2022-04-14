package com.company.payload;

import com.company.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {
    @NotNull(message = "Name can not be empty")
    private String corpName;
    @NotNull(message = "Director name can not be empty")
    private String directorName;
    @NotNull(message="Address must be included")
    private Address address;
}
