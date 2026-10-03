# Aviform

Application de formulation nutritionnelle animale avec API Spring Boot et interface Angular.

## Base de données XAMPP

L’API utilise MySQL via MySQL Connector/J. Les réglages par défaut supposent que MySQL écoute sur `localhost:3306`, avec l’utilisateur `root` et sans mot de passe.

1. Dans le panneau XAMPP, démarrez **MySQL**.
2. Ouvrez phpMyAdmin (`http://localhost/phpmyadmin`) et créez une base de données nommée `aviform`, avec le jeu de caractères `utf8mb4` et la collation `utf8mb4_unicode_ci`.
3. Depuis la racine du projet, démarrez l’API :

	```sh
	./mvnw spring-boot:run
	```

Hibernate crée ou met à jour les tables au démarrage. Les matières premières et profils initiaux sont chargés par l’application si la base est vide.

Si votre configuration XAMPP utilise un autre port ou un mot de passe, fournissez les paramètres avant le démarrage :

```sh
export SPRING_DATASOURCE_URL='jdbc:mysql://localhost:3307/aviform'
export SPRING_DATASOURCE_USERNAME='root'
export SPRING_DATASOURCE_PASSWORD='votre-mot-de-passe'
./mvnw spring-boot:run
```

La base H2 n’est utilisée que par les tests automatisés. Pour les exécuter sans démarrer XAMPP :

```sh
./mvnw test
```
# Aviform
