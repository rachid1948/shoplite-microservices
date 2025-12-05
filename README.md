# 🛒 ShopLite Microservices

ShopLite est une plateforme e-commerce développée en architecture microservices
utilisant Java 21, Spring Boot 3 et Spring Cloud.  
Ce projet a pour but d’illustrer une architecture moderne, scalable et prête pour la production.

---

## 🏗️ Architecture globale

L'application est composée de plusieurs services indépendants, communiquant via REST (synchrones)
et RabbitMQ (asynchrones). Elle inclut également une stack complète d’infrastructure : Gateway,
Discovery, Config Server, Observabilité, CI/CD, Sécurité, etc.

---

## 🧩 Microservices prévus

### 🔹 Services plateforme (infrastructure)
- **config-server** – configuration centralisée (Spring Cloud Config)
- **discovery-server** – registre Eureka pour découvrir les services
- **api-gateway** – Gateway / reverse proxy / sécurité
- **auth-server (Keycloak)** – authentification / autorisation via OAuth2 + JWT

### 🔹 Services métier
- **customer-service** – gestion des clients
- **product-service** – catalogue produits
- **order-service** – gestion des commandes
- **payment-service** – simulation de paiement
- **inventory-service** – gestion du stock
- **notification-service** – envoi de notifications (event-driven)

---

## 🛠️ Stack Technique

### Backend
- Java 21
- Spring Boot 3
- Spring Web / Data JPA / Validation
- Spring Cloud : Gateway, Eureka, Config, OpenFeign, Resilience4j

### Messaging
- RabbitMQ

### Bases de données
- PostgreSQL
- MongoDB (pour vues catalogue)
- Redis (cache)

### Observabilité
- Sleuth + Zipkin (tracing)
- Prometheus + Grafana (metrics)
- ELK Stack (logs centralisés)

### DevOps & Infrastructure
- Docker & Docker Compose
- Kubernetes + Helm (plus tard)
- GitHub Actions (CI)
- SonarQube (qualité)
- ArgoCD (CD / GitOps)

### Sécurité
- Keycloak
- Spring Security + OAuth2 Resource Server
- JWT
- HTTPS / TLS

---

## 🎯 Objectifs pédagogiques

- Comprendre une architecture microservices **complète**
- Maîtriser les bonnes pratiques backend (clean code, séparation des responsabilités, DDD léger)
- Découvrir la communication synchrone & asynchrone
- Déployer et orchestrer plusieurs services
- Mettre en place une chaîne CI/CD professionnelle
- Appliquer une vraie stratégie Git (main, develop, feature branches)

---

## 🚧 Statut actuel

☑️ Étape 0 : préparation du repo et documentation  
🔜 Étape 1 : créer l’infrastructure de base (Config, Eureka, Gateway)

---

## 👤 Auteur

Projet réalisé dans un cadre pédagogique pour apprendre l’architecture microservices de manière professionnelle.

