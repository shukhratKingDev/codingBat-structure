package com.company.coding_com.dto;

import com.company.coding_com.entity.model.BadgeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProblemDto {
    @NotNull(message = "Name field can not be empty")
    private String name;
    @NotNull(message = "text field can not be empty")
    private String textOfProblem;
    @NotNull(message = "Answer field can not be empty")
    private String answer;
    private String solutionVideoLink;
    @NotNull(message = "Badge type field can not be empty")
    private BadgeType badgeType;

}
