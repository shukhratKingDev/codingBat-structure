package com.company.coding_com.dto;

import com.company.coding_com.entity.Problem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThemeDto {
    @NotNull(message = "Name field can not be empty")
    private String name;
    private List<Problem> problems;
}
