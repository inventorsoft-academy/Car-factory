CREATE TABLE Cars (
  car_id       SERIAL      NOT NULL,
  brand        VARCHAR(45) NOT NULL,
  model        VARCHAR(45) NOT NULL,
  created_date DECIMAL     NOT NULL,
  color        VARCHAR(45) NOT NULL,
  price        FLOAT       NOT NULL,
  PRIMARY KEY (car_id)
);

CREATE TABLE Parts (
  part_id SERIAL      NOT NULL,
  name    VARCHAR(45) NOT NULL,
  type    VARCHAR(45) NOT NULL,
  price   FLOAT       NOT NULL,
  used    BOOLEAN     NOT NULL DEFAULT FALSE,
  car_id  INT,
  PRIMARY KEY (part_id),
  FOREIGN KEY (car_id)
  REFERENCES Cars (car_id)
);