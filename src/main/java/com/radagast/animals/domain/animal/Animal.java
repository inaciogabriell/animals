package com.radagast.animals.domain.animal;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.radagast.animals.domain.food.Food;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Table(name = "animal")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Animal {

    @Id
    @GeneratedValue
    private UUID id;

    private Boolean type;
    private String kind;
    private String animalSpecies;
    private Integer age;
    private String name;
    private String sex;
    private String owner;
    private String author;
    private String habitat;
    private String imgUrl;
    private LocalDateTime wasAdded;

    @ManyToMany(mappedBy = "animals", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Food> foods = new HashSet<>();
}
