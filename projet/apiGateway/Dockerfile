# Étape 1 : build avec Maven
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app

# Copier le pom.xml et télécharger les dépendances
COPY pom.xml .
RUN mvn dependency:go-offline

# Copier tout le code et builder
COPY . .
RUN mvn clean package -DskipTests

# Étape 2 : image d'exécution
FROM openjdk:17
WORKDIR /app

# Copier le fichier .jar du conteneur builder
COPY --from=builder /app/target/*.jar app.jar

# Exposer le port (ex: 8081 pour la gateway)
EXPOSE 8080

# Lancer l'app
ENTRYPOINT ["java", "-jar", "app.jar"]
