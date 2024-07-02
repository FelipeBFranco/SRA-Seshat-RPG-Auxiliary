package com.example.seshatrpgauxiliary.infrastructure.persistence.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "character", schema = "seshat")
public class Character {

    @Id
    private Long id;

    private String name;

    private byte[] image;

    @OneToOne
    private Attributes attributes;

    @OneToMany
    @JoinTable(
            name = "character_inventory",
            schema = "seshat",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "inventory_id")
    )
    @JsonManagedReference
    private List<Inventory> inventory;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String race;

    @Column(name = "class")
    private String classType;
}
