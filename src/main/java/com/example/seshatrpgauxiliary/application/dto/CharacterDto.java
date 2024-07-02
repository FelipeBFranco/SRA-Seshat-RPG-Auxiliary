package com.example.seshatrpgauxiliary.application.dto;

import com.example.seshatrpgauxiliary.infrastructure.persistence.entity.Attributes;
import com.example.seshatrpgauxiliary.infrastructure.persistence.entity.Inventory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CharacterDto {

    private Long id;

    private String name;

    private byte[] image;

    private Attributes attributes;

    private List<Inventory> inventory;

    private Long userId;

    private String userName;

    private String race;

    private String classType;
}