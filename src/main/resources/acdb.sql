DROP DATABASE IF EXISTS db;
CREATE DATABASE db;

USE db;

CREATE TABLE users(
  username VARCHAR(50) NOT NULL UNIQUE,
  password VARCHAR(50) NOT NULL,
  email VARCHAR(50) NOT NULL,
  PRIMARY KEY(username)
);

CREATE TABLE bootcamps(
  id INTEGER NOT NULL UNIQUE,
  location VARCHAR(50) NOT NULL,
  start_date DATE NOT NULL,
  end_date DATE NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE codecadets(
  username VARCHAR(50) NOT NULL,
  gender VARCHAR(50) NOT NULL,
  address VARCHAR(150) NOT NULL,
  city VARCHAR(50) NOT NULL,
  phone_number VARCHAR(15) NOT NULL,
  bootcamp INT,
  date DATE NOT NULL,
  PRIMARY KEY (username),
  FOREIGN KEY (username) REFERENCES users(username),
  FOREIGN KEY (bootcamp) REFERENCES bootcamps(id)
);
