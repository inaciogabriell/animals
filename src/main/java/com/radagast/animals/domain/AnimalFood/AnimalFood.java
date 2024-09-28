package com.radagast.animals.domain.AnimalFood;

import com.radagast.animals.domain.animal.Animal;
import com.radagast.animals.domain.food.Food;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "animal_food")
@Setter
@Getter
@IdClass(AnimalFoodId.class)
public class AnimalFood implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @Id
    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;

}
