CREATE DATABASE M2L;

USE M2L;


CREATE TABLE LIGUE (
  id_ligue INT NOT NULL AUTO_INCREMENT,
  nom_ligue VARCHAR(255) NOT NULL,
  PRIMARY KEY (id_ligue));

CREATE TABLE EMPLOYE (
  id_emp INT NOT NULL AUTO_INCREMENT,
  nom_emp VARCHAR(45) NOT NULL,
  prenom_emp VARCHAR(45) NOT NULL,
  mail_emp VARCHAR(255) NOT NULL,
  password_emp VARCHAR(255) NOT NULL,
  admin_ligue BOOLEAN NULL,
  super_admin BOOLEAN NULL,
  id_ligue INT NULL,
  PRIMARY KEY (id_emp, id_ligue),
    FOREIGN KEY (id_ligue)
    REFERENCES LIGUE (id_ligue)
);
