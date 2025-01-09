# Guide de contribution

Ce document décrit les règles et bonnes pratiques pour collaborer efficacement.

## Table des matières
1. [Comment contribuer](#comment-contribuer)
2. [Branches et workflow Git](#branches-et-workflow-git)
3. [Style de code](#style-de-code)
4. [Rapport de bugs](#rapport-de-bugs)
5. [Proposition de fonctionnalités](#proposition-de-fonctionnalités)
6. [Processus de revue de code](#processus-de-revue-de-code)
7. [Communication](#communication)

---

### 1. Comment contribuer
- Forkez ce dépôt et clonez votre fork en local.
- Créez une branche pour votre contribution (`git checkout -b nom-de-branche`).
- Apportez vos modifications et testez-les.
- Faites un commit clair et concis (`git commit -m "Description de la modification"`).
- Poussez votre branche sur votre fork (`git push origin nom-de-branche`).
- Ouvrez une Pull Request (PR) via l'interface GitHub.

---

### 2. Branches et workflow Git
- **main** : Contient la dernière version stable du projet.
- **develop** : Contient les développements en cours.
- Les branches spécifiques aux fonctionnalités doivent suivre le format `feature/nom-fonctionnalite`.
- Les branches pour corriger les bugs doivent suivre le format `fix/description-bug`.

---

### 3. Style de code
- Respectez les normes de développement Java
- Utilisez des commentaires clairs et utiles.
- Exécutez les outils de formatage et de linting avant de soumettre une PR.

---

### 4. Rapport de bugs
Pour signaler un bug :
1. Vérifiez d'abord que le bug n'a pas déjà été signalé.
2. Ouvrez un "Issue" avec les informations suivantes :
   - Description du bug
   - Étapes pour reproduire le problème
   - Comportement attendu
   - Captures d'écran ou logs (si applicable)

---

### 5. Proposition de fonctionnalités
Pour proposer une nouvelle fonctionnalité :
1. Vérifiez qu'une discussion n'est pas déjà en cours dans les Issues ou les Discussions.
2. Créez un nouveau "Issue" avec une description claire de la fonctionnalité proposée.

---

### 6. Processus de revue de code
- Chaque PR sera examinée par au moins un autre membre de l'équipe.
- Les commentaires doivent être constructifs et respectueux.
- Les PR doivent être approuvées avant leur fusion dans la branche principale.

---

### 7. Communication
- Utilisez les Issues pour les bugs et suggestions.