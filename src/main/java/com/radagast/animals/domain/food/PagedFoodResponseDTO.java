package com.radagast.animals.domain.food;

import java.util.List;

public record PagedFoodResponseDTO (
        List<FoodResponseDTO> foods,
        long totalItems,
        int totalPages
) {
}
