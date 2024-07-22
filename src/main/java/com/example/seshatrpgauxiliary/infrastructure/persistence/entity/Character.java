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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String image;

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

    @OneToMany
    @JoinTable(
            name = "character_skill",
            schema = "seshat",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    @JsonManagedReference
    private List<Skill> skill;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String race;

    @Column(name = "class")
    private String classType;

    private String campaign;
}
