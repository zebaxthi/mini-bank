# mini-bank

Proyecto Spring Boot de ejemplo para un backend bancario pequeño.

## Qué cubre
- Autenticación con JWT y API key
- Transacciones bancarias con concurrencia y locking
- CAP: consistencia fuerte en transacciones y reporting eventual con eventos asíncronos
- Contenedores y despliegue en Kubernetes

## Docker Compose
1. `docker compose up --build`
2. Swagger UI: `http://localhost:8080/swagger-ui/index.html`

> Esta opción usa PostgreSQL. El servicio monta `docker/postgres-init.sql` para crear el esquema y datos de ejemplo en la base de datos al iniciar.

## Kubernetes
1. Construye la imagen: `docker build -t mini-bank-app:latest .`
2. Aplica los manifiestos:
   - `kubectl apply -f k8s/postgres-init-configmap.yaml`
   - `kubectl apply -f k8s/postgres-statefulset.yaml`
   - `kubectl apply -f k8s/postgres-service.yaml`
   - `kubectl apply -f k8s/configmap.yaml`
   - `kubectl apply -f k8s/secret.yaml`
   - `kubectl apply -f k8s/deployment.yaml`
   - `kubectl apply -f k8s/service.yaml`
   - `kubectl apply -f k8s/ingress.yaml`

3. Añade esta línea a tu archivo hosts local si quieres usar Ingress con host name:
   - `127.0.0.1 mini-bank.local`
4. Accede desde el navegador a:
   - `http://mini-bank.local`

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
1. Construye la imagen: `docker build -t mini-bank-app:latest .`
2. Aplica los manifiestos:
   - `kubectl apply -f k8s/postgres-init-configmap.yaml`
   - `kubectl apply -f k8s/postgres-statefulset.yaml`
   - `kubectl apply -f k8s/postgres-service.yaml`
   - `kubectl apply -f k8s/configmap.yaml`
   - `kubectl apply -f k8s/secret.yaml`
   - `kubectl apply -f k8s/deployment.yaml`
   - `kubectl apply -f k8s/service.yaml`
   - `kubectl apply -f k8s/ingress.yaml`

3. Añade esta línea a tu archivo hosts local si quieres usar Ingress con host name:
   - `127.0.0.1 mini-bank.local`
4. Accede desde el navegador a:
   - `http://mini-bank.local`

## Notas
- La app usa PostgreSQL en Docker Compose y Kubernetes.
- En Kubernetes la app se conecta a PostgreSQL mediante `SPRING_DATASOURCE_URL`.
- El modo de `docker run` directo ya no está documentado aquí; para uso local rápido, usa `docker compose up --build`.
