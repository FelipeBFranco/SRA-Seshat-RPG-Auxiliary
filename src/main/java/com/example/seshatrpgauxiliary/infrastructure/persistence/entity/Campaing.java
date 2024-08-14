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
@Table(name = "campaing", schema = "seshat")
public class Campaing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Integer gm;

    @ManyToOne
    @JoinTable(
            name = "user_campaing",
            schema = "seshat",
            joinColumns = @JoinColumn(name = "campaing_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonBackReference
    private User user;
}
