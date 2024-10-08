# Running the Application

## Option 1. Running the application using docker-compose

If you want both the application and the PostgreSQL database to be automatically started, use the
`docker-compose.yml` file located in the `./docker` directory.

### 1. Navigate to the docker directory

In the terminal, navigate to the `docker` directory where the `docker-compose.yml` file is located.

### 2. Run docker-compose

Start both services (Spring Boot and PostgreSQL) by running:
`docker-compose up --build`

This command will build the application, start the PostgreSQL database, and the Spring Boot application.  
The application will be accessible at: http://localhost:8080

### 3. Swagger

After starting the application, the API documentation via Swagger will be available at:  
http://localhost:8080/swagger-ui/index.html

---

## Option 2. Running the application using Dockerfile and manually starting PostgreSQL

### 1. Build the Spring Boot application image

To build the application image, run the following command in the project's root directory where the `Dockerfile` is
located:  
`docker build -t luxmed-app:tag .`

### 2. Start the PostgreSQL database

You can start the PostgreSQL container using the following command:

```bash
docker run --name postgres-db \
-e POSTGRES_USER=luxmed_user \
-e POSTGRES_PASSWORD=luxmed_pass \
-e POSTGRES_DB=LuxMedDb \
-p 5432:5432 -d postgres:15.2
```

### 3. Run the Spring Boot application

After starting the database, you can start the application by running:

```bash
docker run -d --name luxmed-app \
--link postgres-db:db \
-e APP_DB_HOST=db:5432 \
-e APP_DB_NAME=LuxMedDb \
-e APP_DB_USERNAME=luxmed_user \
-e APP_DB_PASSWORD=luxmed_pass \
-p 8080:8080 luxmed-app:1.1.1
```

### 4. Swagger

After starting the application, the API documentation via Swagger will be available at:
http://localhost:8080/swagger-ui/index.html

