package com.radagast.animals.domain.animal;

import org.springframework.web.multipart.MultipartFile;

public record AnimalRequestDTO(
        Boolean type,
        String kind,
        String animalSpecies,
        Integer age,
        String name,
        String sex,
        String owner,
        String author,
        String habitat,
        MultipartFile image
) {
}
