package com.radagast.animals.entities;

import com.radagast.animals.domain.food.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface FoodRepository extends JpaRepository<Food, UUID> {

    @Query("SELECT f FROM Food f")
    Page<Food> findAllFoods(Pageable pageable);

    @Query("SELECT f FROM Food f JOIN f.animals a WHERE a.id = :animalId")
    List<Food> findByAnimalId(@Param("animalId") UUID animalId);

}