# mini-bank

Proyecto Spring Boot de ejemplo para un backend bancario pequeño.

## Qué cubre
- Autenticación con JWT y API key
- Transacciones bancarias con concurrencia y locking
- CAP: consistencia fuerte en transacciones y reporting eventual con eventos asíncronos
- Contenedores y despliegue en Kubernetes

## Ejecutar localmente
1. `mvn clean package`
2. `java -jar target/mini-bank-0.0.1-SNAPSHOT.jar`
3. Accede a:
   - `http://localhost:8080/api/auth/login`
   - `http://localhost:8080/api/accounts`
   - `http://localhost:8080/api/transactions/transfer`
   - `http://localhost:8080/api/admin/reports` (requiere API key)

## Credenciales iniciales
- Usuario: `alice@example.com` / `password123`
- Usuario: `bob@example.com` / `password123`
- API key: `super-secret-api-key`

## Seguridad
- JWT: `Authorization: Bearer <token>`
- API Key: `X-API-KEY: super-secret-api-key`

## Kubernetes
1. Construye la imagen: `docker build -t mini-bank:latest .`
2. Aplica los manifiestos:
   - `kubectl apply -f k8s/postgres-deployment.yaml`
   - `kubectl apply -f k8s/postgres-service.yaml`
   - `kubectl apply -f k8s/configmap.yaml`
   - `kubectl apply -f k8s/secret.yaml`
   - `kubectl apply -f k8s/deployment.yaml`
   - `kubectl apply -f k8s/service.yaml`

## Notas
- La app usa H2 en memoria por defecto cuando no hay variables de entorno de base de datos.
- En Kubernetes la app se conecta a PostgreSQL mediante `SPRING_DATASOURCE_URL`.
