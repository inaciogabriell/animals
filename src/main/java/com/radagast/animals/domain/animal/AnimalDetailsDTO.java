package com.radagast.animals.domain.animal;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record AnimalDetailsDTO(
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
        LocalDateTime wasAdded,
        List<FoodDTO> foods
) {
    public record FoodDTO(
            String name,
            String whereToGet,
            Double price
    ) {
    }
}
