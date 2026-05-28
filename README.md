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

## Docker
### Docker (solo)
1. `docker build -t mini-bank:latest .`
2. `docker run --rm -p 8080:8080 --name mini-bank mini-bank:latest`

> Por defecto corre con H2 en memoria dentro del contenedor. Para Postgres usa Docker Compose o Kubernetes.

### Docker Compose (con Postgres)
1. `docker compose up --build`
2. Swagger UI: `http://localhost:8080/swagger-ui/index.html`

## Credenciales iniciales
- Usuario: `alice@example.com` / `password123`
- Usuario: `bob@example.com` / `password123`
- API key: `super-secret-api-key`

## Seguridad
- JWT: `Authorization: Bearer <token>`
- API Key: `X-API-KEY: super-secret-api-key`

## API Docs (Swagger/OpenAPI)
- Swagger UI: `http://localhost:8080/swagger-ui/index.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

### Cómo usar (rápido)
1. Inicia la app.
2. Haz login para obtener un JWT:
   - `POST http://localhost:8080/api/auth/login`
3. Abre Swagger UI y pulsa **Authorize**.
   - **Bearer (JWT)**: pega el token como `Bearer <token>`
   - **API Key** (admin): usa `X-API-KEY: super-secret-api-key`
4. Recarga Swagger UI si ves el esquema vacío (el OpenAPI JSON está protegido).
5. Prueba endpoints:
   - `GET /api/accounts`
   - `POST /api/transactions/transfer`
   - `GET /api/admin/reports` (requiere API key)

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
