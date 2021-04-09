# Logiciel de gestion de personnel de ligues
> Saturnino - Mathieu - Nicolas
## Présentation
Un des responsables de la M2L, utilise une application pour gérer les employés des ligues. L’application est mise à votre disposition par le biais des ressources suivantes :
- Le code source sur ![Github.](https://github.com/alexandreMesle/personnel)
- ![La documentation.](https://enseignement.alexandre-mesle.com/PPE/personnel/javadoc/)
- Une bibliothèque logiciel de dialogue en ligne de commande, disponible dans ![ce dépôt.](https://github.com/alexandreMesle/CommandLine) 

Cette application, très simple, n’existe qu’en ligne de commande et est mono-utilisateur. Nous souhaiterions désigner un administrateur par ligue et lui confier la tâche de recenser les employés de sa ligue. Une partie du travail est déjà faite mais vous allez devoir le compléter.

## Spécification du besoin
Les niveaux d’habilitation des utilisateurs sont les suivants :
- Un simple employé de ligue peut ouvrir l’application et s’en servir comme un annuaire, mais il ne dispose d’aucun droit d’écriture.
- Un employé par ligue est admininstrateur et dispose de droits d’écriture peut gérer la liste des emloyés de sa propre ligue avec une application bureau.
- Le super-admininstrateur a accès en écriture à tous les employés des ligues. Il peut aussi gérer les comptes des administrateurs des ligues avec une application accessible en ligne de commande. 
L’application doit être rendue multi-utilisateurs grace à l’utilisation d’une base de données.
Les trois niveaux d’habilitation ci-dessus doivent être mis en place. 

---

