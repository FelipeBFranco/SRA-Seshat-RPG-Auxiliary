package com.example.seshatrpgauxiliary.presentation.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CharacterCreationRequest {

        private Long userId;
        private String name;
        private String race;
        private String classType;
        private Integer level;
        private Integer health;
        private Integer stamina;
        private Integer strength;
        private Integer agility;
        private Integer intelligence;
        private Integer mind;
        private Integer block;
        private Integer dodge;
        private Integer determination;
        private String image;
        private String campaign;
}
