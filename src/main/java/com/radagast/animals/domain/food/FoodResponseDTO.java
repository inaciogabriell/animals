package com.radagast.animals.domain.food;

import java.time.LocalDateTime;
import java.util.UUID;

public record FoodResponseDTO(
        UUID id,
        String name,
        String whereToGet,
        Double price,
        LocalDateTime wasAdded
) {
}
