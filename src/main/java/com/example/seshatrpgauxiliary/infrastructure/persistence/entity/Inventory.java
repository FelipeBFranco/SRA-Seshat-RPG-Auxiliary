package com.example.seshatrpgauxiliary.infrastructure.persistence.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "inventory", schema = "seshat")
public class Inventory {

        @Id
        private Long id;

        private String name;

        private Integer quantity;

        private String description;

        private String Energy;

        @ManyToOne
        @JoinColumn(name = "character_id")
        @JsonBackReference
        private Character character;

}
