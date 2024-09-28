package com.radagast.animals.domain.AnimalFood;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
public class AnimalFoodId implements Serializable {

    private UUID animal;
    private UUID food;

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AnimalFoodId that = (AnimalFoodId) obj;
        return Objects.equals(animal, that.animal) && Objects.equals(food, that.food);

    }

    @Override
    public int hashCode() {
        return Objects.hash(animal, food);
    }
}
