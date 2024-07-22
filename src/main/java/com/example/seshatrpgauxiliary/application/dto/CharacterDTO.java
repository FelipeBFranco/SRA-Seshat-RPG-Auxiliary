package com.example.seshatrpgauxiliary.application.dto;

import com.example.seshatrpgauxiliary.infrastructure.persistence.entity.Attributes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CharacterDTO {

    private Long id;

    private String name;

    private String image;

    private Attributes attributes;

    private Long userId;

    private String userName;

    private String race;

    private String classType;

    private String campaign;
}