# 🎩 IMDB Movie API

A **Spring Boot** application that processes **IMDB datasets** using **Spring Batch**, stores data in an **H2 database**, and exposes REST APIs for movie-related queries.

---

## Before start Application Add resource import file in root resource as follow
- ✅ name.basics.tsv
- ✅ title.akas.tsv
- ✅ title.basics.tsv
- ✅ title.crew.tsv
- ✅ title.episode.tsv
- ✅ title.principals.tsv
- ✅ title.ratings.tsv


---

## **🚀 Features**
- ✅ **Import IMDB dataset (`.tsv` files) into H2 Database** using **Spring Batch**
- ✅ **RESTful APIs** for searching movies, actors, and best-rated titles
- ✅ **Custom Exception Handling** with meaningful error responses
- ✅ **Spring Validation (`@NotBlank`)** for input validation
- ✅ **Integration Tests** with **MockMvc & H2 Database**
- ✅ **H2 Console Support** for database inspection

---

## **🚀 What To Do In Future**
- ✅ **use Mapper Like mapstruct**
- ✅ **use DTO**
- ✅ **Apply TEST Pyramid**
- ✅ **Add Index in database in where clause and join conditions**
- ✅ **Apply version controller and migration like Liquibase of Flyway**

---

## **🛠️ Technologies Used**
- **Spring Boot 3.4.1**
- **Spring Batch** (for dataset import)
- **Spring Data JPA** (for database interaction)
- **H2 Database** (for in-memory storage)
- **Spring Validation** (`@NotBlank`)
- **JUnit & MockMvc** (for testing)

---

### **3️⃣ Build & Run**
#### **Using Maven**
```sh
mvn clean install
mvn spring-boot:run
```

## **🔍 Testing**
Run all tests using:
```sh
mvn test
```
---

## **🌏 API Endpoints**
| HTTP Method | Endpoint | Description |
|------------|----------|-------------|
| **GET** | `/api/v1/movie/same-director-writer` | Get movies where director & writer are the same person |
| **GET** | `/api/v1/movie/titles-by-actors?actor1=Tom&actor2=Brad` | Get movies where both actors played together |
| **GET** | `/api/v1/movie/best-titles-by-genre?genre=Action` | Get best-rated movies in a genre |

---

## **🖊️ Example API Requests**
### **1️⃣ Get Movies with Same Director & Writer**
```sh
curl -X GET http://localhost:8080/api/v1/movie/same-director-writer
```

### **2️⃣ Get Titles Where Two Actors Starred**
```sh
curl -X GET "http://localhost:8080/api/v1/movie/titles-by-actors?actor1=Tom%20Cruise&actor2=Brad%20Pitt"
```

### **3️⃣ Get Best Titles by Genre**
```sh
curl -X GET "http://localhost:8080/api/v1/movie/best-titles-by-genre?genre=Action"
```

---


Includes **integration tests** for:
- API request validation
- Exception handling
- Database persistence

---

## **📊 Database Access**
H2 Console available at:
```
http://localhost:8080/h2-console
```
Use **JDBC URL**: `jdbc:h2:mem:movie`

