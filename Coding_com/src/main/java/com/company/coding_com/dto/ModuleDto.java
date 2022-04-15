package com.company.coding_com.dto;

import com.company.coding_com.entity.Theme;
import com.company.coding_com.entity.model.ModuleName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.LifecycleState;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModuleDto {
    @NotNull(message = "Module name must be inserted")
    private ModuleName moduleName;
    private List<Theme>themeList;
}
