package com.example.codingbatcom.payLoad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private Integer id;

    @NotNull
    private String name;
    @NotNull
    private String reference;
    @NotNull
    private Integer numberDoneExtraTasks = 0;
    @NotNull
    private Set<Integer> applicationId;
}
