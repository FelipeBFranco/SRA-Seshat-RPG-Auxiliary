package com.example.seshatrpgauxiliary.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SkillDTO {
    private Long id;
    private String name;
    private String description;
    private String energy;
    private Long characterId;
}
