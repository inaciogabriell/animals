CREATE TABLE animal_food (
    food_id UUID NOT NULL,
    animal_id UUID NOT NULL,
    PRIMARY KEY (food_id, animal_id),
    FOREIGN KEY (food_id) REFERENCES food(id),
    FOREIGN KEY (animal_id) REFERENCES animal(id)
);