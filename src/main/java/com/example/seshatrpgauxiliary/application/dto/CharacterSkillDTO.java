package com.example.seshatrpgauxiliary.application.dto;

import com.example.seshatrpgauxiliary.infrastructure.persistence.entity.Skill;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CharacterSkillDTO {

    private Long id;

    private String name;

    private List<Skill> skill;
}

