package com.example.seshatrpgauxiliary.infrastructure.persistence.entity;

import com.example.seshatrpgauxiliary.domain.enums.SkillType;
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
@Table(name = "skill", schema = "seshat")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private SkillType type;

    private String energy;

    @ManyToOne
    @JoinTable(
            name = "character_skill",
            schema = "seshat",
            joinColumns = @JoinColumn(name = "skill_id"),
            inverseJoinColumns = @JoinColumn(name = "character_id")
    )
    @JsonBackReference
    private Character character;
}
