package com.company.coding_com.entity;

import com.company.coding_com.entity.model.ModuleName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated
    @Column(nullable = false)
    private ModuleName moduleName;
    @OneToMany
    private List<Theme> themes;

}
