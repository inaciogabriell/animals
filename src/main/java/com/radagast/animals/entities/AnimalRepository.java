package com.radagast.animals.entities;

import com.radagast.animals.domain.animal.Animal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface AnimalRepository  extends JpaRepository<Animal, UUID> {

    @Query("SELECT a FROM Animal a")
    Page<Animal> findAllAnimals(Pageable pageable);

    @Query("SELECT a FROM Animal a " +
            "WHERE (:type IS NULL OR a.type = :type) " +
            "AND (:kind = '' OR a.kind LIKE %:kind%) " +
            "AND (:animalSpecies = '' OR a.animalSpecies LIKE %:animalSpecies%) " +
            "AND (:age IS NULL OR a.age = :age) " +
            "AND (:sex = '' OR a.sex LIKE %:sex%) " +
            "AND (:author = '' OR a.author LIKE %:author%)" +
            "AND (:habitat = '' OR a.habitat LIKE %:habitat%)")
    Page<Animal> findFilteredAnimals(
            @Param("type") Boolean type,
            @Param("kind") String kind,
            @Param("animalSpecies") String animalSpecies,
            @Param("age") Integer age,
            @Param("sex") String sex,
            @Param("author") String author,
            @Param("habitat") String habitat,
            Pageable pageable
    );

}
