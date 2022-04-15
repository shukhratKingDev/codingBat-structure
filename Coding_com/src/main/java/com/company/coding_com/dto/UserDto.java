package com.company.coding_com.dto;

import com.company.coding_com.entity.Problem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @NotNull(message = "Email can not be empty")
    private String email;
    @NotNull(message = "Password can not be empty")
    private String password;
    private Integer totalScore;
    private List<Problem> problem;
}
