package com.radagast.animals.domain.food;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.radagast.animals.domain.animal.Animal;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Table(name = "food")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Food {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String whereToGet;
    private Double price;
    private LocalDateTime wasAdded;

    @ManyToMany
    @JoinTable(
            name = "animal_food",
            joinColumns = @JoinColumn(name = "food_id"),
            inverseJoinColumns = @JoinColumn(name = "animal_id")
    )
    @JsonBackReference
    private Set<Animal> animals = new HashSet<>();

}
