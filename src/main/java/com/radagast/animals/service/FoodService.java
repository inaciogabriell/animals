package com.radagast.animals.service;

import com.radagast.animals.domain.animal.Animal;
import com.radagast.animals.domain.food.Food;
import com.radagast.animals.domain.food.FoodRequestDTO;
import com.radagast.animals.domain.food.FoodResponseDTO;
import com.radagast.animals.entities.AnimalRepository;
import com.radagast.animals.entities.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private AnimalRepository animalRepository;

    public Food createFood(FoodRequestDTO data) {

        Food newFood = new Food();

        newFood.setName(data.name());
        newFood.setWhereToGet(data.whereToGet());
        newFood.setPrice(data.price());

        foodRepository.save(newFood);

        return newFood;

    }

    public Food addNewFoodToAnimal(UUID animalId, FoodRequestDTO foodData) {

        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new IllegalArgumentException("Animal not found."));

        Food food = new Food();

        food.setName(foodData.name());
        food.setWhereToGet(foodData.whereToGet());
        food.setPrice(foodData.price());

        food.getAnimals().add(animal);

        return foodRepository.save(food);
    }

    public Food addFoodToAnimal(UUID foodId, UUID animalId) {

        Food existingFood = foodRepository.findById(foodId)
                .orElseThrow(() -> new IllegalArgumentException("Food not found."));

        Animal newAnimal = animalRepository.findById(animalId)
                .orElseThrow(() -> new IllegalArgumentException("Animal not found."));

        existingFood.getAnimals().add(newAnimal);

        return foodRepository.save(existingFood);

    }

    public List<FoodResponseDTO> getAllFoods(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Food> foodsPage = this.foodRepository.findAllFoods(pageable);
        return foodsPage.map(
                food -> new FoodResponseDTO(
                    food.getId(),
                    food.getName(),
                    food.getWhereToGet(),
                    food.getPrice()
                )
        )
                .stream().toList();

    }

    public List<Food> consultFoods(UUID animalId) {
        return foodRepository.findByAnimalId(animalId);
    }

}
