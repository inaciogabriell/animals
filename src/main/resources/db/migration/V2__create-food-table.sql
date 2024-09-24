CREATE TABLE food (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    where_to_get VARCHAR(250),
    price REAL
);