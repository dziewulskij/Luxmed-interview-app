# Uruchomienie aplikacji

## Opcja 1. Uruchomienie aplikacji za pomocą docker-compose

Jeśli chcesz, aby zarówno aplikacja, jak i baza danych PostgreSQL zostały uruchomione automatycznie, użyj pliku
docker-compose.yml, który znajduje się w katalogu ./docker.

### 1. Przejdź do katalogu docker

W terminalu przejdź do katalogu docker, gdzie znajduje się plik docker-compose.yml

### 2. Uruchom docker-compose

Uruchom obie usługi (Spring Boot oraz PostgreSQL) za pomocą:
`docker-compose up --build`

Polecenie to zbuduje aplikację, uruchomi bazę danych PostgreSQL i aplikację Spring Boot.
Aplikacja będzie dostępna pod adresem: http://localhost:8080

### 3. Swagger

Po uruchomieniu aplikacji, dokumentacja API w Swaggerze będzie dostępna pod adresem:
http://localhost:8080/swagger-ui/index.html

---

## Opcja 2. Uruchomienie aplikacji z użyciem Dockerfile i ręcznie uruchomionej bazy PostgreSQL

### 1. Zbuduj obraz aplikacji Spring Boot

Aby zbudować obraz aplikacji, wykonaj polecenie w katalogu głównym projektu, gdzie znajduje się plik Dockerfile:
`docker build -t luxmed-app:tag .`

### 2. Uruchom bazę danych PostgreSQL

Możesz uruchomić kontener PostgreSQL za pomocą następującego polecenia:
`docker run --name postgres-db
-e POSTGRES_USER=luxmed_user
-e POSTGRES_PASSWORD=luxmed_pass
-e POSTGRES_DB=LuxMedDb
-p 5432:5432 -d postgres:15.2`

### 3. Uruchom aplikację Spring Boot

Po uruchomieniu bazy danych możesz uruchomić aplikację:
`docker run --name luxmed-app --link postgres-db:db
-e APP_DATASOURCE_HOST=db:5432/mydb
-e APP_DATASOURCE_USERNAME=luxmed_user
-e APP_DATASOURCE_PASSWORD=luxmed_pass
-p 8080:8080 luxmed-app:tag`

### 4. Swagger

Po uruchomieniu aplikacji, dokumentacja API w Swaggerze będzie dostępna pod adresem:
http://localhost:8080/swagger-ui/index.html

