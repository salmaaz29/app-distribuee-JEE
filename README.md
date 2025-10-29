🧑‍🎓 Application de Gestion des Étudiants — Jakarta EE
📖 Description

Cette application web distribuée permet de gérer les étudiants à l’aide de Jakarta EE.
Elle est composée de deux modules :

getudiants-ejb : contient la logique métier et la couche de persistance.

getudiants-web : gère l’interface utilisateur avec JSF (Jakarta Server Faces).

🛠️ Prérequis

JDK 17+

Maven 3.8+

WildFly 37.0.1.Final

MySQL 8.0+

⚙️ Installation

Cloner le projet :

git clone https://github.com/salmaaz29/app-distribuee-JEE
cd app-distribuee-JEE


Créer la base de données :

CREATE DATABASE Getudiants;


Configurer le driver MySQL et la datasource dans WildFly.

Déployer les modules :

cd getudiants-ejb
mvn clean install wildfly:deploy

cd ../getudiants-web
mvn clean package wildfly:deploy

🚀 Utilisation

Accéder à l’application :
👉 http://localhost:8080/getudiants-web/

✨ Fonctionnalités

CRUD complet (ajouter, modifier, supprimer, consulter)

Interface web basée sur JSF

Persistance avec JPA / Hibernate

Transactions via JTA

🧩 Structure du projet
app-distribuee/
├── getudiants-ejb/      # Logique métier et persistance
├── getudiants-web/      # Interface utilisateur (JSF)
└── pom.xml              # POM parent
