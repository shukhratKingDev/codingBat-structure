package com.company.payload;

import com.company.entity.Company;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DepartmentDto {
    @NotNull(message = "This field can not be empty")
    private String name;
    @NotNull(message = "This field can not be empty")
    private Company company;
}
