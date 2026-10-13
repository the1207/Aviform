# Aviform

Application de formulation nutritionnelle animale avec API Spring Boot et interface Angular.

## Base de données PostgreSQL / Supabase

L’API utilise PostgreSQL via le pilote JDBC PostgreSQL. L’URL par défaut cible le projet Supabase configuré dans `application.properties`; le mot de passe doit être fourni dans la variable d’environnement `SPRING_DATASOURCE_PASSWORD`.

Pour Supabase, configurez l’URL JDBC, l’utilisateur et le mot de passe de la base via les variables d’environnement avant de démarrer l’API. Utilisez les informations de connexion PostgreSQL fournies par Supabase et ajoutez `?sslmode=require` à l’URL JDBC si nécessaire :

```sh
export SPRING_DATASOURCE_URL='jdbc:postgresql://<hote>:5432/postgres?sslmode=require'
export SPRING_DATASOURCE_USERNAME='<utilisateur>'
export SPRING_DATASOURCE_PASSWORD='<mot-de-passe>'
./mvnw spring-boot:run
```

Hibernate crée ou met à jour les tables au démarrage. Les matières premières et profils initiaux sont chargés par l’application si la base est vide.

La base H2 n’est utilisée que par les tests automatisés. Pour les exécuter :

```sh
./mvnw test
```
# Aviform
