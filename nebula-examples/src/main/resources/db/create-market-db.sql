DROP TABLE IF EXISTS Products;

CREATE TABLE Products (
  id INTEGER PRIMARY KEY,
  name VARCHAR(25),
  description VARCHAR(4096),
  price DOUBLE,
  category INTEGER
);

DROP TABLE IF EXISTS Category;

CREATE TABLE Category (
  id INTEGER PRIMARY KEY,
  name VARCHAR(25),
  description VARCHAR(4096),
);