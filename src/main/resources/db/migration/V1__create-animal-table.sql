CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE animal (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    type BOOLEAN NOT NULL,
    kind VARCHAR(100) NOT NULL,
    animal_species VARCHAR(150),
    age INTEGER,
    name VARCHAR(100),
    sex VARCHAR(20),
    owner VARCHAR(100),
    author VARCHAR(100) NOT NULL,
    habitat VARCHAR(100),
    img_url VARCHAR(500)
);