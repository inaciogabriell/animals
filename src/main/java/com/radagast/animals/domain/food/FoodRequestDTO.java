package com.radagast.animals.domain.food;

public record FoodRequestDTO(
        String name,
        String whereToGet,
        Double price
) {
}
