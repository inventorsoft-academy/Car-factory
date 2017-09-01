CREATE TABLE IF NOT EXISTS parts (
  id    SERIAL           NOT NULL,
  type  TEXT             NOT NULL,
  price DOUBLE PRECISION NOT NULL,
  used  BOOLEAN          NOT NULL DEFAULT FALSE,
  PRIMARY KEY (id)
)