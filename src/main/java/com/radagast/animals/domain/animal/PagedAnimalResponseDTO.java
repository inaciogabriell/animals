package com.radagast.animals.domain.animal;

import java.util.List;

public record PagedAnimalResponseDTO(
        List<AnimalResponseDTO> animals,
        long totalItems,
        int totalPages
) {}
