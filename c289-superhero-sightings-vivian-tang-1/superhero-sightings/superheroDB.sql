DROP DATABASE IF EXISTS superheroDB;
CREATE DATABASE superheroDB;
USE superheroDB;

CREATE TABLE hero_villain (
  `hero_villain_id` INT PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL UNIQUE,
  `description` VARCHAR(50) NOT NULL
);

CREATE TABLE location (
  `location_id` INT PRIMARY KEY AUTO_INCREMENT,
  `address1` VARCHAR(50) NOT NULL,
  `address2` VARCHAR(50),
  `city` VARCHAR(50) NOT NULL,
  `state` VARCHAR(50) NOT NULL,
  `zip` CHAR(5) NOT NULL,
  `latitude` DECIMAL(8,6) NOT NULL,
  `longitude` DECIMAL(9,6) NOT NULL
);

CREATE TABLE hero_villain_sighting (
  `sighting_id` INT PRIMARY KEY AUTO_INCREMENT,
  `location_id` INT NOT NULL,
  `hero_villain_id` INT NOT NULL,
  `timestamp` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (`hero_villain_id`) REFERENCES `hero_villain`(`hero_villain_id`),
  FOREIGN KEY (`location_id`) REFERENCES `location`(`location_id`)
);

CREATE TABLE organization (
  `organization_id` INT PRIMARY KEY AUTO_INCREMENT ,
  `name` VARCHAR(50) NOT NULL,
  `description` VARCHAR(50),
  `contact_info` VARCHAR(50) NOT NULL,
  `location_id` INT NOT NULL UNIQUE,
  FOREIGN KEY (`location_id`) REFERENCES `location`(`location_id`)
);

CREATE TABLE hero_villain_organization (
  `hero_villain_organization_id` INT PRIMARY KEY AUTO_INCREMENT,
  `hero_villain_id` INT NOT NULL,
  `organization_id` INT NOT NULL,
  FOREIGN KEY (`hero_villain_id`) REFERENCES `hero_villain`(`hero_villain_id`),
  FOREIGN KEY (`organization_id`) REFERENCES `organization`(`organization_id`)
);

CREATE TABLE hero_villain_powers (
  `power_id` INT PRIMARY KEY AUTO_INCREMENT,
  `hero_villain_id` INT NOT NULL,
  `description` VARCHAR(50),
  FOREIGN KEY (`hero_villain_id`) REFERENCES `hero_villain`(`hero_villain_id`)
);