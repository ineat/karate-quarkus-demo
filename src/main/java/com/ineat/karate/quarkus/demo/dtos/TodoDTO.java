package com.ineat.karate.quarkus.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
public class TodoDTO {
    public String id;

    @NotBlank
    public String title;

    @NotBlank
    public String description;

    @NotBlank
    public Integer priority;
}