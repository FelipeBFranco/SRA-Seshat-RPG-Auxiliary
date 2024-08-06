package com.example.seshatrpgauxiliary.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDTO {
    private Long id;
    private String name;
    private Integer quantity;
    private String description;
    private String energy;
    private Long characterId;
}
