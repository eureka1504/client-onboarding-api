# Client Onboarding API

RESTful API built with Java Spring Boot for managing client onboarding workflows.

# Tech Stack
- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database
- Maven

# API Endpoints

## Get All Clients

**Endpoint**

```http
GET /clients
```

**Response**

```json
[
  {
    "id": 1,
    "name": "John Doe",
    "email": "john@example.com",
    "status": "NEW"
  }
]
```

---

## Get Client By ID

**Endpoint**

```http
GET /clients/{id}
```

**Example**

```http
GET /clients/1
```

**Response**

```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john@example.com",
  "status": "NEW"
}
```

---

## Create Client

**Endpoint**

```http
POST /clients
```

**Request Body**

```json
{
  "name": "Jane Doe",
  "email": "jane@example.com"
}
```

**Response**

```json
{
  "id": 2,
  "name": "Jane Doe",
  "email": "jane@example.com",
  "status": "NEW"
}
```

---

## Update Client Status

**Endpoint**

```http
PUT /clients/{id}/status
```

**Request Body**

```json
{
  "status": "UNDER_REVIEW"
}
```

**Response**

```json
{
  "id": 2,
  "name": "Jane Doe",
  "email": "jane@example.com",
  "status": "UNDER_REVIEW"
}
```