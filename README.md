# Hint JPA POC

Petit projet Spring Boot 3 + JPA + H2 montrant comment appliquer des hints/commentaires SQL côté repository et côté `EntityManager`.

## Démarrer
- Prérequis : Java 17+ et Maven.
- Lancer : `mvn spring-boot:run`
- H2 console : `http://localhost:8080/h2-console` (JDBC url `jdbc:h2:mem:hintdb`, user `sa`, mdp vide).

## Endpoints de démo
- `GET /books/title/{title}` : repository avec `@QueryHints` (`org.hibernate.comment`) sur `findByTitle`.
- `GET /books/author/{author}` : requête JPQL via `EntityManager` avec `setHint` (`org.hibernate.readOnly` + commentaire).
- `GET /books/author-native/{author}` : repository + requête native avec commentaire inline `/*+ HINT: repository-native */`.

## Voir les hints dans les logs
- Les commentaires sont activés par `spring.jpa.properties.hibernate.use_sql_comments=true` et `spring.jpa.show-sql=true` dans `src/main/resources/application.properties`.
- Lancer l'app puis appeler un endpoint, par exemple :
  - `curl http://localhost:8080/books/title/Clean%20Architecture`
  - `curl http://localhost:8080/books/author/Eric%20Evans`
  - `curl http://localhost:8080/books/author-native/Eric%20Evans`
- Observer la console : les requêtes SQL affichent les commentaires/hints (`/* hint: ... */` ou `/*+ HINT: repository-native */`). Bindings visibles via `logging.level.org.hibernate.orm.jdbc.bind=TRACE`.

## Données
Des livres sont insérés au démarrage (voir `src/main/java/com/example/hintjpademo/config/DataInitializer.java`) pour tester immédiatement.***
