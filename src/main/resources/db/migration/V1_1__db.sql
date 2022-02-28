CREATE TABLE recipe
(
    id serial PRIMARY KEY ,
    name varchar(100) NOT NULL , -- название кофе
    duration int NOT NULL  -- время изготовления (мин)
);

CREATE TABLE ingrid
(
    id serial PRIMARY KEY ,
    name varchar(20) NOT NULL ,
    required int NOT NULL ,
    recipe_id int NOT NULL REFERENCES recipe(id)
);

CREATE TABLE coffee
(
    id serial PRIMARY KEY ,
    name varchar(100) NOT NULL ,
    readytime timestamptz NOT NULL
);

CREATE TABLE storage
(
    name varchar(20) PRIMARY KEY,
    saved int NOT NULL
);