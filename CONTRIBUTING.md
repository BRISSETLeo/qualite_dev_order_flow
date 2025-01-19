# Guide de contribution

Merci de vouloir contribuer à ce projet ! Votre aide est grandement appréciée. Pour garantir une collaboration efficace et harmonieuse, veuillez suivre les directives suivantes.


## **Comment contribuer**

### 1. **Signaler un bug**
- Vérifiez d'abord que le bug n'a pas déjà été signalé dans les [Issues](#).

- Si ce n'est pas le cas, créez un nouveau rapport de bug :
  - Décrivez le problème de manière claire.

  - Ajoutez des étapes pour reproduire le bug.
  - Mentionnez l'environnement (OS, navigateur, version du projet, etc.).

### 2. **Proposer une amélioration ou une nouvelle fonctionnalité**
- Consultez la section des [Issues](#) pour voir si une proposition similaire existe.

- Si non, ouvrez une **issue** :
  - Expliquez en détail votre idée.

  - Ajoutez des exemples ou des cas d'utilisation pertinents.

### 3. **Soumettre des modifications**
- Forkez le dépôt et clonez votre fork localement.

- Créez une branche pour vos modifications :
  ```bash
  git checkout -b feature/ma-nouvelle-fonctionnalite
  ```
- Ajoutez vos commits :
  - Écrivez des messages de commit clairs et explicites.

  - Suivez la convention suivante pour les messages de commit :
    ```
    <type>(<scope>): <description>
    ```
    Exemples : 
    - `feat(panier): ajouter une option de suppression de produit`

    - `fix(stock): corriger un bug de mise à jour des niveaux`
- Poussez vos modifications :
  ```bash
  git push origin feature/ma-nouvelle-fonctionnalite
  ```

- Créez une **pull request (PR)** vers la branche principale du dépôt :
  - Fournissez une description détaillée de vos changements.
  - Liez la PR aux issues concernées (le cas échéant).

---

## **Règles de communication**
- Restez respectueux et constructif dans vos échanges.

- Privilégiez des discussions ouvertes et des solutions collaboratives.
- Si vous avez des doutes, posez vos questions dans les [discussions](#) ou ajoutez un commentaire dans votre PR.

---

## **Normes de codage**
- Respectez le style de code utilisé dans le projet. Consultez le fichier `.editorconfig` ou les guides spécifiques.

- Utilisez des outils de linting et de formatage pour garantir un code propre et uniforme :
  - Exemple : **ESLint**, **Prettier**, **Black** ou **flake8**.
- Documentez les fonctions et classes importantes.

---

## **Tests**
- Écrivez des tests pour toute nouvelle fonctionnalité ou correction.
- Assurez-vous que tous les tests passent avant de soumettre une PR 
- Ajoutez des tests unitaires et fonctionnels lorsque cela est pertinent.

---

## **Processus de validation**
1. Une PR est examinée par les mainteneurs ou d'autres contributeurs.
2. Les feedbacks sont partagés dans les commentaires de la PR.
3. Une fois approuvée, la PR est fusionnée par un mainteneur.

Merci encore pour votre contribution et votre engagement !