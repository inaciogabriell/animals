package com.radagast.animals.controllers;

import com.radagast.animals.domain.animal.AnimalResponseDTO;
import com.radagast.animals.domain.food.Food;
import com.radagast.animals.domain.food.FoodRequestDTO;
import com.radagast.animals.domain.food.FoodResponseDTO;
import com.radagast.animals.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/animals/food")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @PostMapping
    public ResponseEntity<Food> createFood(@RequestBody FoodRequestDTO data) {

        FoodRequestDTO foodRequestDTO = new FoodRequestDTO(
                data.name(),
                data.whereToGet(),
                data.price());

        Food newFood = this.foodService.createFood(foodRequestDTO);
        return ResponseEntity.ok(newFood);

    }

    @PostMapping("/animal/{animalId}")
    public ResponseEntity<Food> addNewFoodToAnimal(@PathVariable UUID animalId,
                                                   @RequestBody FoodRequestDTO data) {

        Food foods = foodService.addNewFoodToAnimal(animalId, data);
        return ResponseEntity.ok(foods);

    }

    @PostMapping("/addFoodToAnimal/{animalId}/{foodId}")
    public ResponseEntity<Food> addFoodToAnimal(@PathVariable UUID animalId,
                                                @PathVariable UUID foodId) {

        Food updatedFood = foodService.addFoodToAnimal(foodId, animalId);
        return ResponseEntity.ok(updatedFood);

    }

    @DeleteMapping("/{foodId}")
    public ResponseEntity<Void> deleteFood(@PathVariable UUID foodId) {
        foodService.deleteFood(foodId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<FoodResponseDTO>> getFood(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size) {
        List<FoodResponseDTO> allFoods = this.foodService.getAllFoods(page, size);
        return ResponseEntity.ok(allFoods);
    }

}
