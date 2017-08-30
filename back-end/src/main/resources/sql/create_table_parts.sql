CREATE TABLE parts (
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