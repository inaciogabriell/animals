package com.radagast.animals.controllers;

import com.radagast.animals.domain.animal.Animal;
import com.radagast.animals.domain.animal.AnimalDetailsDTO;
import com.radagast.animals.domain.animal.AnimalRequestDTO;
import com.radagast.animals.domain.animal.AnimalResponseDTO;
import com.radagast.animals.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/animals/animal")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Animal> create(@RequestParam("type") Boolean type,
                                         @RequestParam("kind") String kind,
                                         @RequestParam(value = "animalSpecies", required = false) String animalSpecies,
                                         @RequestParam(value = "age", required = false) Integer age,
                                         @RequestParam(value = "name", required = false) String name,
                                         @RequestParam(value = "sex", required = false) String sex,
                                         @RequestParam(value = "owner", required = false) String owner,
                                         @RequestParam("author") String author,
                                         @RequestParam(value = "habitat", required = false) String habitat,
                                         @RequestParam(value = "image", required = false) MultipartFile image) {

        AnimalRequestDTO animalRequestDTO = new AnimalRequestDTO(type, kind, animalSpecies, age, name, sex, owner, author, habitat, image);
        Animal newAnimal = this.animalService.createAnimal(animalRequestDTO);
        return ResponseEntity.ok(newAnimal);

    }

    @GetMapping("/{animalId}")
    public ResponseEntity<AnimalDetailsDTO> getAnimalDetails(@PathVariable UUID animalId) {

        AnimalDetailsDTO animalDetails = animalService.getAnimalDetails(animalId);
        return  ResponseEntity.ok(animalDetails);

    }

    @GetMapping
    public ResponseEntity<List<AnimalResponseDTO>> getAnimal(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size) {

        List<AnimalResponseDTO> allAnimals = this.animalService.getAllAnimals(page, size);
        return ResponseEntity.ok(allAnimals);

    }

    @GetMapping("/filter")
    public ResponseEntity<List<AnimalResponseDTO>> getFilteredAnimals(@RequestParam(defaultValue = "0") int page,
                                                                      @RequestParam(defaultValue = "10") int size,
                                                                      @RequestParam(required = false) Boolean type,
                                                                      @RequestParam(required = false) String kind,
                                                                      @RequestParam(required = false) String animalSpecies,
                                                                      @RequestParam(required = false) Integer age,
                                                                      @RequestParam(required = false) String sex,
                                                                      @RequestParam(required = false) String author,
                                                                      @RequestParam(required = false) String habitat) {

        List<AnimalResponseDTO> animals = animalService.getFilteredAnimals(page, size, type, kind, animalSpecies, age, sex, author, habitat);
        return ResponseEntity.ok(animals);

    }

}
