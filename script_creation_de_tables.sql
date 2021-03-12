CREATE DATABASE M2L;

USE M2L;

CREATE TABLE ligue (
  id_ligue INT AUTO_INCREMENT,
  nom_ligue VARCHAR(255) NOT NULL,
  PRIMARY KEY (id_ligue));

CREATE TABLE employe (
  id_emp INT AUTO_INCREMENT,
  nom_emp VARCHAR(45) NOT NULL,
  prenom_emp VARCHAR(45) NOT NULL,
  mail_emp VARCHAR(255) NOT NULL,
  password_emp VARCHAR(255) NOT NULL,
  date_arrive DATE,
  date_depart DATE,
  admin_ligue BOOLEAN NOT NULL DEFAULT 0,
  super_admin BOOLEAN NOT NULL DEFAULT 0,
  id_ligue INT NULL,
  PRIMARY KEY (id_emp),
    FOREIGN KEY (id_ligue)
    REFERENCES ligue (id_ligue) ON DELETE CASCADE
);
