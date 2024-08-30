DROP TABLE IF EXISTS Customer;

CREATE TABLE Customer (
  customerId int NOT NULL,
  name varchar(255) NOT NULL,
  lastname varchar(255) NOT NULL,
  birthday date NOT NULL,
  PRIMARY KEY (customerId)
);