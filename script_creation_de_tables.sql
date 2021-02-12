CREATE DATABASE M2L;

USE M2L;

CREATE TABLE LIGUE (
  id_ligue INT AUTO_INCREMENT,
  nom_ligue VARCHAR(255) NOT NULL,
  PRIMARY KEY (id_ligue));

CREATE TABLE EMPLOYE (
  id_emp INT AUTO_INCREMENT,
  nom_emp VARCHAR(45) NOT NULL,
  prenom_emp VARCHAR(45) NOT NULL,
  mail_emp VARCHAR(255) NOT NULL,
  password_emp VARCHAR(255) NOT NULL,
  date_arrive DATE NOT NULL,
  date_depart DATE,
  admin_ligue BOOLEAN NOT NULL DEFAULT 0,
  super_admin BOOLEAN NOT NULL DEFAULT 0,
  id_ligue INT NULL,
  PRIMARY KEY (id_emp),
    FOREIGN KEY (id_ligue)
    REFERENCES LIGUE (id_ligue)
);
