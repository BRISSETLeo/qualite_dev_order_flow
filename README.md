---
title: README

---

**DEPONT Samuel**
**BLONDEAU Erwan**
**BRISSET Léo**


# EXERCICE 1

## Tâche 1 : Ségrégation des responsabilités 

### 1. Classification des Domaines

#### Domaines principaux :
- **Panier d'achat**
- **Traitement des commandes**

#### Domaines de support :
- **Registre des produits**
- **Catalogue de produits**
- **Gestion des stocks**
- **Gestion des clients**

#### Domaines génériques :
- **Notification**
- **Sourcing d'événements**
- **Monnaie**

---

### 2. Organisation des Applications et Bibliothèques

#### **Applications (`apps`)** :
- **`of-api-gateway`** :  
  - Rôle : Passerelle API pour gérer les requêtes entrantes et les distribuer aux microservices appropriés.
  - Répertoire : `src/main/java/org/ormi/priv/tfa/orderflow/api/gateway`
- **`of-product-registry-microservices`** :
  - Rôle : Microservice pour gérer les opérations liées aux produits.
  - Répertoires :
    - `product.registry` : Code source pour la gestion des produits.
      - **`aggregate`** : Contient les agrégats et les services associés.
      - **`service`** : Contient les consommateurs et producteurs de commandes et événements.
    - `product.registry.read` : Code source pour la lecture des données du registre des produits.
      - **`projection`** : Contient les projecteurs et les dépôts associés.

#### **Bibliothèques (`libs`)** :
- **`event-sourcing`** :
  - Rôle : Gestion de l'event sourcing.
  - Répertoires :
    - **`aggregate`** : Contient les agrégats et les mappers associés.
    - **`store`** : Contient le magasin d'événements.
- **`published-language`** :
  - Rôle : Langages partagés entre les microservices.
  - Répertoires :
    - **`command`** : Commandes du registre des produits.
    - **`event`** : Événements du registre des produits.
    - **`query`** : Requêtes et modèles associés.
    - **`valueobject`** : Objets de valeur et mappers associés.

---

### 3. Responsabilités des Composants

#### **`of-api-gateway`**
- **Responsabilité** :
  - Agit comme une passerelle API pour gérer les requêtes entrantes et les distribuer aux microservices appropriés.
  - Centralise les points d'entrée pour les clients externes.
  - Implémente des contrôleurs pour les opérations liées aux produits via des endpoints HTTP.

#### **`product.registry`**
- **Responsabilité** :
  - Gère les opérations principales du registre des produits.
  - Implémente la logique métier pour l'enregistrement, la mise à jour et la suppression des produits.
  - Contient les agrégats, services et dépôts nécessaires pour manipuler les entités de produits.

#### **`product.registry.read`**
- **Responsabilité** :
  - Gère les opérations de lecture pour le registre des produits.
  - Implémente les projections et dépôts pour fournir des vues matérialisées des données de produits.
  - Consomme les événements produits par `product.registry` pour maintenir les vues de lecture à jour.

#### **`event-sourcing`**
- **Responsabilité** :
  - Fournit des abstractions et implémentations pour la gestion de l'event sourcing.
  - Contient des agrégats, magasins d'événements et mappers pour gérer les événements de domaine.
  - Facilite la persistance et la récupération des événements pour les microservices.

#### **`published-language`**
- **Responsabilité** :
  - Définit les langages partagés entre les microservices, incluant les commandes, événements et requêtes.
  - Contient les objets de valeur, DTOs et mappers nécessaires pour la communication inter-microservices.
  - Facilite la cohérence et la réutilisation des définitions de messages dans l'ensemble du système.


## Tâche 2 : Identifier les concepts principaux

 
**1.Quels sont les concepts principaux utilisés dans l'application Order flow ?**

Les concepts principaux utilisés dans l'application Order flow sont :

Architecture microservices : L'application est composée de plusieurs microservices, chacun étant responsable d'un domaine métier.

Conception pilotée par le domaine  : Les microservices sont conçus en fonction des domaines métiers, avec une séparation claire des responsabilités.

Architecture pilotée par les événements (EDA): L'application utilise le sourcing d'événements pour stocker l'état des agrégats et des entités, et pour communiquer entre les microservices.

CQRS (Command Query Responsibility Segregation) : Ce modèle est utilisé pour séparer les opérations de lecture et d'écriture d'une application.

Gestion des événements ou Sourcing d'événements  : Les événements sont utilisés pour partager l'état de l'application entre les microservices et pour maintenir la cohérence de l'état.

Système de messagerie : Apache Pulsar est utilisé comme courtier de messages pour envoyer et recevoir des messages entre les microservices.

Ces concepts sont implémentés dans les différents microservices et bibliothèques de l'application, comme décrit dans le fichier (doc/business/context-mapping.cml) et les documents de présentation du projet.

**2.Comment les concepts principaux sont-ils implémentés dans les microservices ?**

Architecture microservices :

Chaque domaine métier est un microservice. Par exemple, product.registry pour les commandes et product.registry.read pour les requêtes. Les microservices sont situés dans le dossier apps.

Conception pilotée par le domaine (DDD) :

Les microservices sont conçus autour des domaines métiers définis dans domain.cml. Chaque microservice implémente les agrégats, entités et objets-valeurs spécifiques à son domaine.

Architecture pilotée par les événements (EDA) :

Les événements sont utilisés pour communiquer entre les microservices et pour stocker l'état des agrégats. Par exemple, le microservice product.registry émet des événements de type ProductRegistered, ProductUpdated et ProductRemoved.

CQRS (Command Query Responsibility Segregation) :

Les opérations de lecture et d'écriture sont séparées en utilisant des microservices distincts. Par exemple, product.registry gère les commandes (écriture) et product.registry.read gère les requêtes (lecture).

Gestion des événements :

Les événements sont stockés dans un magasin d'événements (Event Store) et projetés dans des modèles de lecture pour les requêtes. Par exemple, le service ProductRegistryProjector dans ProductRegistryProjector.java consomme les événements et les projette dans le modèle de lecture.

Système de messagerie :

Apache Pulsar est utilisé comme courtier de messages pour envoyer et recevoir des messages entre les microservices. Les configurations de Pulsar sont définies dans les fichiers application.properties des microservices, par exemple application.properties. 

**3.Que fait la bibliothèque libs/event-sourcing ? Comment est-elle utilisée dans les microservices (relation entre métier et structure du code) ?**

La bibliothèque event-sourcing fournit les interfaces et les classes de base nécessaires pour implémenter le sourcing d'événements dans l'application Order flow. Elle est utilisée pour stocker et récupérer les événements qui représentent l'état des agrégats et des entités. 

Utilisation dans les microservices :

Les microservices utilisent la bibliothèque event-sourcing pour implémenter le sourcing d'événements. Par exemple, le microservice product.registry utilise l'interface EventStore pour stocker et récupérer les événements de registre de produits.

Exemple d'utilisation dans ProductRegistryEventRepository :
Le repository ProductRegistryEventRepository implémente l'interface EventStore pour gérer les événements de registre de produits.
ProductRegistryEventRepository



```java
@ApplicationScoped
public class ProductRegistryEventRepository
    implements EventStore<ProductRegistryEventEntity>,
        PanacheMongoRepository<ProductRegistryEventEntity> {

@Override
public void saveEvent(ProductRegistryEventEntity event) {
    persist(event);
}

@Override
public List<ProductRegistryEventEntity>
    findEventsByAggregateRootIdAndStartingVersion(String aggregateRootId,
      long startingVersion) {
    return find(
        "aggregateRootId = ?1 and version > ?2",
        aggregateRootId,
        startingVersion,
        Sort.by("version"))
        .list();
  }
}
```

la bibliothèque event-sourcing fournit les outils nécessaires pour implémenter le sourcing d'événements dans les microservices de l'application Order flow, permettant ainsi de stocker et de récupérer les événements qui représentent l'état des agrégats et des entités.


**4.Comment l'implémentation actuelle de l'event-sourcing assure-t-elle la fiabilité des états internes de l'application ?**

L'implémentation actuelle de l'event-sourcing assure la fiabilité des états internes de l'application de plusieurs manières :

Stockage séquentiel des événements : Les événements sont stockés dans l'ordre de leur création, permettant de reconstruire l'état actuel en les rejouant.
Reconstruction de l'état : Les agrégats sont reconstruits en rejouant les événements associés, garantissant un état cohérent.
Séparation des préoccupations (CQRS) : Les opérations de lecture et d'écriture sont séparées, optimisant et sécurisant chaque type d'opération.
Consistance éventuelle : L'état de l'application devient cohérent à terme, assurant la résilience du système.
Ces mécanismes garantissent que l'état de l'application reste fiable et cohérent malgré les opérations distribuées et asynchrones.

## Tâche 3 : Identifier les problèmes de qualité 

# Exercice 2

## Tâche 1 : Compléter les commentaires et la Javadoc 
```java
/**
 * Classe abstraite représentant un événement dans le registre de produits.
 * 
 * Cette classe est utilisée pour stocker les événements dans la collection MongoDB "product_registry_events".
 */
@MongoEntity(collection = "product_registry_events")
public abstract class ProductRegistryEventEntity {
  
  /**
   * Identifiant unique de l'événement dans MongoDB.
   */
  public ObjectId id;
  
  /**
   * Identifiant unique de l'événement.
   */
  public String eventId;
  
  /**
   * Type de l'événement.
   */
  public String eventType = getEventType();
  
  /**
   * Identifiant de la racine d'agrégat associée à l'événement.
   */
  public String aggregateRootId;
  
  /**
   * Version de l'agrégat au moment de l'événement.
   */
  public long version;
  
  /**
   * Timestamp de l'événement.
   */
  public long timestamp;

  /**
   * Méthode abstraite pour obtenir le type de l'événement.
   * 
   * @return le type de l'événement
   */
  abstract String getEventType();
}
```
## Tâche 2 : Corriger les RuntimeException paresseuses

Dans le code, l'utilisation de **`RuntimeException`** pour gérer les erreurs est problématique car elle est trop générique. Cela rend le débogage difficile et complique la gestion des erreurs dans l'application.

Au lieu d'utiliser des exceptions génériques comme `RuntimeException`, il est recommandé de créer des **exceptions spécifiques** adaptées au contexte du problème rencontré. Cela permet une meilleure lisibilité, facilite le traitement des erreurs et améliore la maintenabilité du code.

#### Étapes pour une gestion d'erreurs correcte

Nous avons créé une nouvelle classe d'exception appelée **`ProductRegistryConsumerCreationException`**. Cette classe hérite de `RuntimeException` et permet de mieux représenter les erreurs liées à la création d’un consommateur Pulsar :

```java
private Consumer<ProductRegistryEvent> getEventsConsumerByCorrelationId(String correlationId) {
    try {
      final String channelName = ProductRegistryEventChannelName.PRODUCT_REGISTRY_EVENT.toString();
      final String topic = channelName + "-" + correlationId;
      return pulsarClients.getClient(channelName)
          .newConsumer(Schema.JSON(ProductRegistryEvent.class))
          .subscriptionName(topic)
          .topic(topic)
          .subscribe();
    } catch (PulsarClientException e) {
      throw new ProductRegistryConsumerCreationException("Failed to create consumer for product registry events.", e);
    }
  }
```

```java
package org.ormi.priv.tfa.orderflow.api.gateway.productregistry.adapter.inbound.http.resource.exception;

public class ProductRegistryConsumerCreationException extends RuntimeException {

    public ProductRegistryConsumerCreationException(String message){
        super(message);
    }

    public ProductRegistryConsumerCreationException(String message, Throwable cause) {
        super(message, cause);
    }

}
```

### Tâche 3 : Convertir les payloads des Entités (couche de persistance) en records

ProductUpdatedEventEntity.java
ProductRegisteredEventEntity.java

```java
/**
* Payload for the event.
*/
public static record Payload (   
    /**
    * The id of the product.
    */
    String productId,
    /**
    * The name of the product.
    */
    String name,
    /**
    * The description of the product.
    */
    String productDescription
){}
```

ProductRemovedEventEntity.java
```java
/**
* Payload for the event.
*/
public static record Payload (
    /**
    * The id of the product.
    */
    String productId
  ) {}
```

A tous les endroits où payload est appelé, il faudra rajouter des parenthèses quand il faudra appeler un attribut du **record**. Par exemple ```payload.name``` sera transformer en ```payload.name()```

### Tâche 4 : Modifier les Entités (couche de persistance) pour utiliser des champs privés avec des accesseurs 

Pour tous les fichiers où il existe un ```public Payload payload;```
```java
/**
* The payload for the event.
*/
private Payload payload;

/**
* Get the payload for the event.
* @return the payload.
*/
public Payload getPayload() {
    return payload;
 }
```

A tous les endroits où on appel directement ```.payload``` il faudra le remplacer par ```.getPayload()```

### Tâche 4.1 : Champs publics sur les entités Panache 

SonarQube m'avertit des champs ```public``` car il souhaite rajouter un **static final** et les instanciés. Cependant ce n'est pas quelque chose de pertinent ici, ces champs doivent représenter des données dynamiques propres à chaque instance de l'entité et non des constantes immuables ou partagées entre toutes les instances. Cette approche est incompatible avec le modèle de persistance des entités qui nécessite des propriétés modifiables pour refléter les données persistées.

![image](https://hackmd.io/_uploads/HJKXGG_NJg.png)


### Tâche 5 : Corriger les erreurs et avertissements signalés par SonarLint 


ProductRegistryService.java
```java
/**
   * Handle the register command, save and return an event.
   * 
   * @param registerProduct - The register product command.
   * @return The product registered event.
   */
@Transactional(Transactional.TxType.MANDATORY)
public Uni<ProductRegistered> registerProduct(ProductRegistry registry, RegisterProduct registerProduct) {
    Log.debug("Registering product: " + registerProduct);
    // Check if the product name is available
    if (!registry.isProductNameAvailable(registerProduct.getName())) {
      // Product name is not available
      return Uni.createFrom()
          .failure(new IllegalArgumentException("Product name already in use: " + registerProduct.getName()));
    }
    // Create and save the event
    final ProductRegistered evt = new ProductRegistered(
        new EventId(),
        PRODUCT_REGISTRY_ID,
        registry.incrementVersion(),
        Instant.now().toEpochMilli(),
        new ProductRegistered.Payload(
            new ProductId(),
            registerProduct.getName(),
            registerProduct.getProductDescription()));
    productRegistryRepository.saveEvent(ProductRegistryEventEntityMapper.INSTANCE.toEntity(evt));

    // Emit the event
    eventEmitter.emit(evt);

    return Uni.createFrom().item(evt);
}
```

ProductRegistryEventConsumer.java
```java
@Incoming("product-registry-event")
@Transactional(Transactional.TxType.REQUIRED)
public void handleEvent(ProductRegistryEvent event) {
    // Project the event
    projector.handleEvent(event);
    // Sink the event here once or while projection is processed
    sinkEvent(event);
}

private void sinkEvent(ProductRegistryEvent event) {
    // Implementation for sinking the event
    // This could involve logging, sending to another service, etc.
    Logger logger = Logger.getLogger(ProductRegistryEventConsumer.class.getName());
    if (logger.isLoggable(java.util.logging.Level.INFO)) {
        logger.info(String.format("Event processed: %s", event.toString()));
    }
}
```

ProductRegistryProjector.java
```java
/**
* Handle the event.
* 
* @param event - the event to handle
*/
public void handleEvent(ProductRegistryEvent event) {
    if (event instanceof ProductRegistered registered) {
      projectRegisteredProduct(registered);
    } else if (event instanceof ProductUpdated updated) {
      projectUpdatedProduct(updated);
    } else if (event instanceof ProductRemoved removed) {
      projectRemovedProduct(removed);
    }
}

  /**
  * Project an updated product.
  * 
  * @param updated - the event to project
  */
  public void projectUpdatedProduct(ProductUpdated updated) {
      // Get the product entity
      final Optional<ProductEntity> result = productService.getProductById(updated.getPayload().productId);
      if (result.isEmpty()) {
        // The product does not exist
        // Log an error
        Log.error("Product not found: " + updated.getPayload().productId);
        return;
      }
      // Update the product
      final ProductEntity product = result.get();
      product.name = updated.getPayload().name;
      product.description = updated.getPayload().productDescription;
      // Persist the product
      productService.updateProduct(product);
  }
```

### Tâche 6 : Ajouter des tests d'intégration 

### Tâche 7 : Ajouter des tests unitaires 

##  Exercice 3 :

### Tâche 1 : Choisir un modèle de contrôle de version et une stratégie de branchement

Modèle proposé : Github

Branche principale (main ou master) :
  - Contient le code stable et prêt à être livré en production.
  - Les contributions ne sont intégrées qu’après validation.
  - Branche de développement (develop) (optionnelle mais utile pour les projets complexes) :
  - Regroupe les fonctionnalités en cours avant d’être fusionnées dans la branche principale.
  - C'est ici que le code est testé et intégré avant la mise en production.

Branches de fonctionnalités (feature/) :
  - Chaque nouvelle fonctionnalité ou tâche est développée dans une branche séparée.
  - Nommée de manière descriptive : feature/ajout-authentification, feature/amélioration-ui.

Branches de correction (hotfix/) :
  - Créées pour corriger rapidement les bugs critiques en production.

Pull requests (PR) :
  - Les modifications sont fusionnées après revue de code par l'équipe.

Raisons de choisir ce modèle :
Collaboration fluide : Les membres peuvent travailler en parallèle sans interférer directement.
    Gestion des conflits : Git facilite la détection et la résolution des conflits.
    Historique clair : Les commits et les branches organisés permettent un suivi précis des modifications.
    Intégration continue : Couplé à un pipeline CI/CD, ce modèle garantit des builds stables.
    Adapté aux équipes distantes : Chaque membre peut travailler localement et synchroniser son travail au besoin.

### Tâche 2 : Définir les responsabilités des équipes

**Membre 1 : Responsable des services principaux (Panier et Commandes)**

Service de panier d'achat :
  - Implémentation des agrégats pour suivre l'état des paniers.
  - Gestion des commandes (ajout/suppression de produits, modifications des quantités).
  - Développement des requêtes pour afficher l'état du panier.

Service de traitement des commandes :
  - Implémentation des agrégats pour gérer le cycle de vie des commandes.
  - Gestion des commandes (validation, annulation, mise à jour).
  - Requêtes pour fournir le statut des commandes.
  - Tests unitaires et fonctionnels pour assurer la robustesse.

**Membre 2 : Responsable des services de soutien (Catalogue, Registre, Stock, Clients)**

Service de registre des produits :
  - Implémentation d’un agrégat unique pour centraliser les produits.
  - Gestion des commandes (ajout/mise à jour des produits).
  - Requêtes pour rechercher des produits.

Service de catalogue de produits :
  - Gestion des agrégats pour structurer les catégories et descriptions.
  - Implémentation des commandes pour organiser le catalogue.
  - Requêtes pour afficher le catalogue.

Service de gestion des stocks :
  - Gestion d’un agrégat unique pour suivre les niveaux de stock.
  - Implémentation des commandes pour réserver ou ajuster le stock.
  - Requêtes pour vérifier la disponibilité.

Service de gestion des clients :
  - Implémentation des agrégats et commandes pour gérer les profils clients.
        Requêtes pour fournir les informations des clients.

**Membre 3 : Responsable des services génériques (Notifications et Infrastructure)**

Service de notification :
  - Implémentation des commandes pour envoyer des notifications (emails, SMS, push).
  - Requêtes pour suivre l’historique des notifications.
    
Sourcing d’événements :
  - Gestion de l’interface d’événements (publication et consommation).
  - Implémentation d’un stockage fiable pour l’historique des événements.
  - Assurer que les événements entre services sont correctement synchronisés.

Service de monnaie :
  - Développement de l’objet-valeur pour gérer les opérations monétaires.
  - Vérification de la précision des calculs.

CI/CD et supervision :
  - Mise en place des pipelines CI/CD pour les tests et le déploiement.
  - Surveillance de la performance et des erreurs (logs, monitoring).

**Objectif : Fournir un support transversal aux autres services pour garantir leur fonctionnement.
Collaboration et polyvalence**

Code Reviews :
  - Chaque membre révise le code d'un autre pour assurer la qualité et partager les connaissances.

Documentation commune :
  - Documentez les API, événements, et structures de données pour aligner les efforts.

Pair programming ponctuel :
  - Travaillez ensemble sur des modules complexes ou des intégrations critiques.

Planification agile :
  - Utilisez des outils comme Trello ou Jira pour suivre les tâches et s’assurer que chacun progresse.

**Gestion des dépendances**

Utilisation des événements :
  - Les services génèrent des événements pour notifier les autres composants des actions importantes (ex. : mise à jour de stock, confirmation de commande).
        
Interfaces bien définies :
  - Les APIs entre les services doivent être documentées (via Swagger/OpenAPI).

```
Résumé

Membre 1 : Panier et Commandes (Services principaux).
Membre 2 : Catalogue, Stock, Registre et Clients (Services de soutien).
Membre 3 : Notifications, Infrastructure, et Sourcing d’événements (Services génériques).
```

### Tâche 3 : README et règles de collaboration 
    
J'ai ajouté une rubrique dans le README pour ceux voulant contribuer au projet avec le lien vers un fichier *CONTRIBUTING.md* avec toutes les règles utiles à la bonne collaboration et à la bonne communication.

### Tâche 4 : Divisez votre groupe en 2 équipes 


Divisez votre groupe en 2 équipes et attribuez les responsabilités de chaque équipe. Vous devez définir les rôles de chaque membre de l'équipe et les canaux de communication entre les équipes (Trello, Mail, Slack, etc.).

::: tip
Les exercices suivants vous demanderont de réaliser les exercices en équipe.
Vous pouvez utiliser des outils de collaboration IDE comme Live Share dans Visual Studio Code pour travailler ensemble.
:::

