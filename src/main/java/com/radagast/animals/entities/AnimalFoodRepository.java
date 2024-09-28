package com.radagast.animals.entities;

import com.radagast.animals.domain.AnimalFood.AnimalFood;
import com.radagast.animals.domain.AnimalFood.AnimalFoodId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface AnimalFoodRepository extends JpaRepository<AnimalFood, AnimalFoodId> {

    @Modifying
    @Query("DELETE FROM AnimalFood af WHERE af.animal.id = :animalId")
    void deleteByAnimalId(UUID animalId);

    @Modifying
    @Query("DELETE FROM AnimalFood af WHERE af.food.id = :foodId")
    void deleteByFoodId(UUID foodId);

}
