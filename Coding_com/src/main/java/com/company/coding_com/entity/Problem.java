package com.company.coding_com.entity;
import com.company.coding_com.entity.model.BadgeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Problem {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false,unique = true)
    private String name;
    @Column(nullable = false)
    private String textOfProblem;
    @Column(nullable = false)
    private String answer;
    private String solutionVideoLink;
    @Enumerated
    @Column(nullable = false)
    private BadgeType badgeType;//if you solve this problem you earn specific number of badges.
}
