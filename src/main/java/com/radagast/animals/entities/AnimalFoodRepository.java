package com.radagast.animals.entities;

import com.radagast.animals.domain.AnimalFood.AnimalFood;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AnimalFoodRepository extends JpaRepository<AnimalFood, UUID> {

    void deleteByAnimalId(UUID animalId);
    void deleteByFoodId(UUID foodId);

}
