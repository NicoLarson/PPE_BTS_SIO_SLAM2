# PPE_BTS_SIO_SLAM2
> Saturnino Mathieu Nicolas

## Itération 1

- [x] Modélisation d’une base de données avec un MCD.
![MCD](https://raw.githubusercontent.com/NicoLarson/PPE_BTS_SIO_SLAM2/master/MCD.png)
- [x] Vérification du fonctionnement correct de l’application grâce à des tests unitaires.
- [x] Gestion de la date de départ et de celle d’arrivée de chaque employé (couche métier + tests unitaires).
- [x] Représentation des menus du dialogue en ligne de commande avec un arbre heuristique (Utilisez un logiciel de type Freemind). 

![TreeMind](https://raw.githubusercontent.com/NicoLarson/PPE_BTS_SIO_SLAM2/master/Menu.png)


## Itération 2

- [x] Script de création de tables

```sql
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

```

- [ ] Saisie des dates avec gestion des erreurs
- [x] Tout le monde a utilisé git
- [ ] Sélection d'un employé avant de décider si le supprime ou le modifie
- [ ] Changement de l'admin d'une ligue en ligne de commande

## Itération 3

- [ ] Création d’une classe fille de Passerelle permettant de gérer la connexion à la base de données avec JDBC (ou avec Hibernate si vous le souhaitez).
- [ ] Modélisation de l’interface graphique avec des maquettes.
- [ ] Possibilité de changer l’administrateur d’une ligue en ligne de commande. 

## Itération 4

- [ ] Création d’une interface graphique (Swing ou JavaFx) pour que les administrateurs puissent gérer les ligues.
- [ ] Si une ligue n’a pas d’administrateur, c’est automatiquement le root qui devient l’administrateur de la ligue (avec les tests unitaires correspondants).
- [ ] Installation de la base de données sur un serveur accessible dans le réseau local de la société.
- [ ] Rédaction d’un mode opératoire à l’usage des administrateurs. 


