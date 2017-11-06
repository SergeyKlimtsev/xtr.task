DROP TABLE IF EXISTS vacancy;
DROP TABLE IF EXISTS employer;


CREATE TABLE employer (
  id       INT PRIMARY KEY,
  name     VARCHAR(255),
  building VARCHAR(255),
  city     VARCHAR(255),
  street   VARCHAR(255)
);

CREATE TABLE vacancy
(
  id          INT PRIMARY KEY,
  name        VARCHAR(255),
  salary_from VARCHAR(255),
  salary_to   VARCHAR(255),
  currency    VARCHAR(255),
  url         VARCHAR(255),
  employer_id INT,
  FOREIGN KEY (employer_id) REFERENCES employer (id)
);

INSERT INTO employer (id, name, building, city, street) VALUES (1, 'name', 'building', 'city', 'street');
INSERT INTO vacancy (id, name, salary_from, salary_to, currency, url, employer_id)
VALUES (2, 'name', 'salary_from', 'salary_to', 'currency', 'url', 1);