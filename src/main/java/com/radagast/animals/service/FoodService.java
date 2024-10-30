package com.radagast.animals.service;

import com.radagast.animals.domain.animal.Animal;
import com.radagast.animals.domain.food.Food;
import com.radagast.animals.domain.food.FoodRequestDTO;
import com.radagast.animals.domain.food.FoodResponseDTO;
import com.radagast.animals.domain.food.PagedFoodResponseDTO;
import com.radagast.animals.entities.AnimalFoodRepository;
import com.radagast.animals.entities.AnimalRepository;
import com.radagast.animals.entities.FoodRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private AnimalFoodRepository animalFoodRepository;

    public Food createFood(FoodRequestDTO data) {

        Food newFood = new Food();

        newFood.setName(data.name());
        newFood.setWhereToGet(data.whereToGet());
        newFood.setPrice(data.price());
        newFood.setWasAdded(LocalDateTime.now());

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
        food.setWasAdded(LocalDateTime.now());

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

    @Transactional
    public void deleteFood(UUID foodId) {
        animalFoodRepository.deleteByFoodId(foodId);
        foodRepository.deleteById(foodId);
    }

    public PagedFoodResponseDTO getAllFoods(int page, int size, String sortOrder) {

        Sort sort = sortOrder.equalsIgnoreCase("desc")
                ? Sort.by("name").descending()
                : Sort.by("name").ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Food> foodsPage = this.foodRepository.findAllFoods(pageable);

        List<FoodResponseDTO> foodResponseList = foodsPage.map(
                food -> new FoodResponseDTO(
                    food.getId(),
                    food.getName(),
                    food.getWhereToGet(),
                    food.getPrice(),
                    food.getWasAdded()))
                .stream().toList();

        long totalItems = foodsPage.getTotalElements();
        int totalPages = (int) Math.ceil((double) totalItems / size);

        return new PagedFoodResponseDTO(foodResponseList, totalItems, totalPages);
    }

    public List<Food> consultFoods(UUID animalId) {
        return foodRepository.findByAnimalId(animalId);
    }

}
