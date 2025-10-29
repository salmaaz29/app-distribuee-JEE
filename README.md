# 🧑‍🎓 Application de Gestion des Étudiants — Jakarta EE

## 📖 Description

Cette application web distribuée permet de gérer les étudiants à l’aide de **Jakarta EE**.  
Elle est composée de deux modules :  
- **ejb-module** : contient la logique métier et la couche de persistance.  
- **web-module** : gère l’interface utilisateur.

---

## 🛠️ Prérequis

- **JDK 17+**  
- **Maven 3.8+**  
- **WildFly 38.0.0.Final**  
- **MySQL 8.0+**  

---

## ⚙️ Installation

1. **Cloner le projet :**
   ```
   git clone https://github.com/salmaaz29/app-distribuee-JEE
   cd app-distribuee-JEE
Créer la base de données :


CREATE DATABASE Getudiants;
Configurer le driver MySQL et la datasource dans WildFly.

Déployer les modules :

  ```
cd getudiants-ejb
mvn clean install wildfly:deploy
cd ../getudiants-web
mvn clean package wildfly:deploy
  ```

## ✨ Fonctionnalités
CRUD complet (ajouter, modifier, supprimer, consulter)

- Interface web
- Persistance avec JPA / Hibernate
- Transactions via JTA

## 🧩 Structure du projet
 ```
app-distribuee/
├── ejb-module/    
│   ├── src/main/java/fstt/getudiants/
│   │   ├── entities/        # Entités JPA
│   │   ├── ejb/             # EJBs + implémentations
│   └── resources/META-INF/
│       └── persistence.xml  
│   └── pom.xml
│
├── web-module/              
│   ├── src/main/java/fstt/getudiants/
│   │   └── web/             # Servlets
│   └── src/main/webapp/
│       ├── WEB-INF/
│       └── Pages JSF 
│   └── pom.xml
│
└── pom.xml                  # POM parent

 ```
