package com.radagast.animals.service;

import com.radagast.animals.domain.animal.*;
import com.radagast.animals.domain.food.Food;
import com.radagast.animals.entities.AnimalFoodRepository;
import com.radagast.animals.entities.AnimalRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class AnimalService {

    @Value("${aws.bucket.name}")
    private String bucketName;

    @Autowired
    private S3Client s3Client;

    @Autowired
    private FoodService foodService;

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private AnimalFoodRepository animalFoodRepository;

    public Animal createAnimal(AnimalRequestDTO data) {

        String imgUrl = null;

        if(data.image() != null) {
            imgUrl = this.uploadImg(data.image());
        }

        Animal newAnimal = new Animal();

        newAnimal.setType(data.type());
        newAnimal.setKind(data.kind());
        newAnimal.setAnimalSpecies(data.animalSpecies());
        newAnimal.setAge(data.age());
        newAnimal.setName(data.name());
        newAnimal.setSex(data.sex());
        newAnimal.setOwner(data.owner());
        newAnimal.setAuthor(data.author());
        newAnimal.setHabitat(data.habitat());
        newAnimal.setImgUrl(imgUrl);
        newAnimal.setWasAdded(LocalDateTime.now());

        animalRepository.save(newAnimal);

        return newAnimal;

    }

    @Transactional
    public void deleteAnimal(UUID animalId) {
            animalFoodRepository.deleteByAnimalId(animalId);
            animalRepository.deleteById(animalId);
    }

    public AnimalDetailsDTO getAnimalDetails(UUID animalId) {

        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new IllegalArgumentException("Animal not found."));

        List<Food> foods = foodService.consultFoods(animalId);

        List<AnimalDetailsDTO.FoodDTO> foodDTOs = foods.stream()
                .map(food -> new AnimalDetailsDTO.FoodDTO(
                        food.getName(),
                        food.getWhereToGet(),
                        food.getPrice()))
                .toList();
                //.collect(Collectors.toList());

        return new AnimalDetailsDTO(
                animal.getId(),
                animal.getType(),
                animal.getKind(),
                animal.getAnimalSpecies(),
                animal.getAge(),
                animal.getName(),
                animal.getSex(),
                animal.getOwner(),
                animal.getAuthor(),
                animal.getHabitat(),
                animal.getImgUrl(),
                animal.getWasAdded(),
                foodDTOs);
    }

    public PagedAnimalResponseDTO getAllAnimals(int page, int size, String sortOrder) {

        Sort sort = sortOrder.equalsIgnoreCase("desc")
                ? Sort.by("kind").descending()
                : Sort.by("kind").ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Animal> animalsPage = this.animalRepository.findAllAnimals(pageable);

        List<AnimalResponseDTO> animalResponseList = animalsPage.map(
                animal -> new AnimalResponseDTO(
                    animal.getId(),
                    animal.getType(),
                    animal.getKind(),
                    animal.getAnimalSpecies(),
                    animal.getAge(),
                    animal.getName(),
                    animal.getSex(),
                    animal.getOwner(),
                    animal.getAuthor(),
                    animal.getHabitat(),
                    animal.getImgUrl(),
                    animal.getWasAdded()))
                .stream().toList();

        long totalItems = animalsPage.getTotalElements();
        int totalPages = (int) Math.ceil((double) totalItems / size);

        return new PagedAnimalResponseDTO(animalResponseList, totalItems, totalPages);
    }

    public  PagedAnimalResponseDTO getFilteredAnimals (int page, int size, Boolean type, String kind, String animalSpecies, Integer age, String sex, String author, String habitat, String sortOrder) {

        kind = (kind != null) ? kind : "";
        animalSpecies = (animalSpecies != null) ? animalSpecies : "";
        sex = (sex != null) ? sex : "";
        author = (author != null) ? author : "";
        habitat = (habitat != null) ? habitat : "";

        Sort sort = sortOrder.equalsIgnoreCase("desc")
                ? Sort.by("kind").descending()
                : Sort.by("kind").ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Animal> animalsPage = this.animalRepository.findFilteredAnimals(type, kind, animalSpecies, age, sex, author, habitat, pageable);

        List<AnimalResponseDTO> animalResponseList = animalsPage.map(
                animal -> new AnimalResponseDTO(
                    animal.getId(),
                    animal.getType(),
                    animal.getKind(),
                    animal.getAnimalSpecies(),
                    animal.getAge(),
                    animal.getName(),
                    animal.getSex(),
                    animal.getOwner(),
                    animal.getAuthor(),
                    animal.getHabitat(),
                    animal.getImgUrl(),
                    animal.getWasAdded()))
                .stream().toList();

        long totalItems = animalsPage.getTotalElements();
        int totalPages = (int) Math.ceil((double) totalItems / size);

        return new PagedAnimalResponseDTO(animalResponseList, totalItems, totalPages);
    }

    private  String getDirectUrl(String imgName) {
        String encodedImgName = imgName.replace(" ", "+");
        return "https://" + bucketName + ".s3.amazonaws.com/" + encodedImgName;
    };

    private String uploadImg(MultipartFile multipartFile) {

        String imgName = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();

        try {
            File file = this.convertMultipartToFile(multipartFile);

            String encodedImgName = imgName.replace(" ", "+");

            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(imgName)
                    .build();

            PutObjectResponse response = s3Client.putObject(putObjectRequest, RequestBody.fromFile(file));

            file.delete();

            return getDirectUrl(encodedImgName);
        } catch (Exception e) {
            System.out.println("Erro ao subir arquivo de imagem: " + e.getMessage());
            return "";
        }

    }

    private File convertMultipartToFile(MultipartFile multipartFile) throws IOException {

        File convFile = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convFile);

        fos.write(multipartFile.getBytes());
        fos.close();
        return convFile;
    }

}
