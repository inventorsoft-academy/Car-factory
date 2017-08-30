CREATE TABLE cars (
  car_id       SERIAL      NOT NULL,
  brand        VARCHAR(45) NOT NULL,
  model        VARCHAR(45) NOT NULL,
  created_date DECIMAL     NOT NULL,
  color        VARCHAR(45) NOT NULL,
  price        FLOAT       NOT NULL,
  PRIMARY KEY (car_id)
);