package com.example.seshatrpgauxiliary.application.dto;

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
public class CharacterInventoryDTO {

    private Long id;

    private String name;

    private List<Inventory> inventory;
}
