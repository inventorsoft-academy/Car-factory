CREATE TABLE IF NOT EXISTS parts (
  _id    SERIAL           NOT NULL,
  type  TEXT             NOT NULL,
  price DOUBLE PRECISION NOT NULL,
  used  BOOLEAN          NOT NULL DEFAULT FALSE,
  PRIMARY KEY (_id)
)