package com.radagast.animals.domain.animal;

import java.time.LocalDateTime;
import java.util.UUID;

public record AnimalResponseDTO(
        UUID id,
        Boolean type,
        String kind,
        String animalSpecies,
        Integer age,
        String name,
        String sex,
        String owner,
        String author,
        String habitat,
        String imgUrl,
        LocalDateTime wasAdded
) {
}
