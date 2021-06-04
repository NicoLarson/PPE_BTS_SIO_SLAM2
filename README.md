# Logiciel de gestion de personnel de ligues
## Présentation
Un des responsables de la M2L, utilise une application pour gérer les employés des ligues. L’application est mise à votre disposition par le biais des ressources suivantes :
- Le code source sur [Github.](https://github.com/alexandreMesle/personnel)
- [La documentation.](https://enseignement.alexandre-mesle.com/PPE/personnel/javadoc/)
- Une bibliothèque logiciel de dialogue en ligne de commande, disponible dans ![ce dépôt.](https://github.com/alexandreMesle/CommandLine) 

Cette application, très simple, n’existe qu’en ligne de commande et est mono-utilisateur. Nous souhaiterions désigner un administrateur par ligue et lui confier la tâche de recenser les employés de sa ligue. Une partie du travail est déjà faite mais vous allez devoir le compléter.

## Spécification du besoin
Les niveaux d’habilitation des utilisateurs sont les suivants :
- Un simple employé de ligue peut ouvrir l’application et s’en servir comme un annuaire, mais il ne dispose d’aucun droit d’écriture.
- Un employé par ligue est admininstrateur et dispose de droits d’écriture peut gérer la liste des emloyés de sa propre ligue avec une application bureau.
- Le super-admininstrateur a accès en écriture à tous les employés des ligues. Il peut aussi gérer les comptes des administrateurs des ligues avec une application accessible en ligne de commande. 
L’application doit être rendue multi-utilisateurs grace à l’utilisation d’une base de données.
Les trois niveaux d’habilitation ci-dessus doivent être mis en place. 

## Itération 1
- Modélisation d’une base de données avec un MCD.

![](https://raw.githubusercontent.com/NicoLarson/PPE_BTS_SIO_SLAM2/Clean/documentation/MCD.png)
- Vérification du fonctionnement correct de l’application grâce à des tests unitaires.
- Gestion de la date de départ et de celle d’arrivée de chaque employé (couche métier + tests unitaires).
- Représentation des menus du dialogue en ligne de commande avec un arbre heuristique (Utilisez un logiciel de type Freemind). 
![](https://raw.githubusercontent.com/NicoLarson/PPE_BTS_SIO_SLAM2/Clean/documentation/Menu.png)

## Itération 2

- Création de la base de données.
[Script de création](https://github.com/NicoLarson/PPE_BTS_SIO_SLAM2/blob/Clean/documentation/script_creation_de_tables.sql)

- Gestion des dates dans le dialogue en ligne de commande.
- Dans le dialogue en ligne de commande, un employé doit être selectionné avant que l’on puisse choisir de modifier ou de supprimer.
- Possibilité de changer l’administrateur d’une ligue en ligne de commande. 

## Itération 3

- Création d’une classe fille de Passerelle permettant de gérer la connexion à la base de données avec JDBC (ou avec Hibernate si vous le souhaitez).
- Modélisation de l’interface graphique avec des maquettes. [Maquettes](https://github.com/NicoLarson/PPE_BTS_SIO_SLAM2/blob/Clean/documentation/maquettes_interface_graphique.pdf)
- Possibilité de changer l’administrateur d’une ligue en ligne de commande. 

## Itération 4

- Création d’une interface graphique (Swing ou JavaFx) pour que les administrateurs puissent gérer les ligues.
- Si une ligue n’a pas d’administrateur, c’est automatiquement le root qui devient l’administrateur de la ligue (avec les tests unitaires correspondants).
- Installation de la base de données sur un serveur accessible dans le réseau local de la société.
- Rédaction d’un mode opératoire à l’usage des administrateurs. 
[Mode opératoire](https://github.com/NicoLarson/PPE_BTS_SIO_SLAM2/blob/Clean/documentation/Mode_operatoire_Gestion_du_personnel.pdf)
## Contraintes
- Conserver java pour l’application.
- Utiliser le versionnement avec git
- Utiliser la bibliothèque de dialogue en ligne de commande fournie. 

---

> TAVARES MONTEIRO Saturnino - RABEYRIN Mathieu - YANG Nicolas - 2021
