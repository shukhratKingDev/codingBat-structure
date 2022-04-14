package com.company.payload;

import com.company.entity.Address;
import com.company.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkerDto {
    @NotNull(message = "This field can not be empty")
    private String name;
    @NotNull(message = "This field can not be empty")
    private String phoneNumber;
    @NotNull(message = "This field can not be empty")
    private Address address;
    @NotNull(message = "This field can not be empty")
    private Department department;
}
