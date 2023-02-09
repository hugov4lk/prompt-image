CREATE TABLE person
(
    id   bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name varchar(256) NOT NULL
);

CREATE TABLE car
(
    id          bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    person_id   bigint NOT NULL,
    make        varchar(256) NOT NULL,
    model       varchar(256) NOT NULL,
    numberplate varchar(256) NOT NULL,
    FOREIGN KEY (person_id) REFERENCES person (id)
);