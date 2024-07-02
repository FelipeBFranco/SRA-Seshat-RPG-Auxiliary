package com.example.seshatrpgauxiliary.domain.enums;

import lombok.Getter;

@Getter
public enum SkillType {
    PASSIVA("Habilidade passiva"),
    ATIVA("Habilidade ativa"),
    REACAO("Habilidade de reação"),
    CARACTERISTICA("Característica");

    private final String description;

    SkillType(String description) {
        this.description = description;
    }

}