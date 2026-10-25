DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS cars;
DROP TABLE IF EXISTS mechanics;
DROP TABLE IF EXISTS clients;

CREATE TABLE clients(
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    phone VARCHAR(20) NOT NULL
);

CREATE TABLE mechanics(
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    specialization VARCHAR(50) NOT NULL
);


CREATE TABLE cars(
    id SERIAL PRIMARY KEY,
    brand VARCHAR(50) NOT NULL,
    model VARCHAR(50) NOT NULL,
    year SMALLINT NOT NULL CHECK (year BETWEEN 1950 AND 2100),
    license_plate VARCHAR(20) NOT NULL UNIQUE,
    owner_id INTEGER NOT NULL REFERENCES clients(id)
);



CREATE TABLE orders (
    id    SERIAL      PRIMARY KEY,
    description   TEXT NOT NULL,
    cost          NUMERIC(10,2) NOT NULL,
    status VARCHAR(30) NOT NULL DEFAULT 'IN_PROGRESS' CHECK
        (status IN ('IN_PROGRESS','COMPLETED','CANCELLED')),
    car_id        INTEGER  NOT NULL REFERENCES cars(id),
    mechanic_id   INTEGER  NOT NULL REFERENCES mechanics(id)
);