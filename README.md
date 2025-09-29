# Shopping Cart Implementation - Sanlam Technical Test
![Sanlam Logo](/src/main/resources/assets/sanlam-logo.jpg)

**Candidate:** Fanelesibonge Mbuyazi  
**Role:** Software Engineer I (Sanlam FinTech Technical Test)  

---

## 📖 Overview
This project is an implementation of the **Shopping Cart Exercise** provided as part of the **Sanlam FinTech Software Engineer I technical assessment**.  

The application simulates basic e-commerce cart operations such as:
- Adding items to a cart
- Calculating the total cost of items in the cart

The solution emphasizes:
- Clean layered architecture (Controller, Service, Model, DTO)
- Use of `BigDecimal` for currency correctness
- Readability, maintainability, and extensibility

---

## 🏗️ Project Structure

```
shopping-cart/
├── controller/          # REST controllers
├── service/             # Business logic
├── model/               # Domain models (Cart, CartItem)
│   └── exception/       # Custom exceptions
├── dto/                 # Response DTOs
├── resources/           # Application resources
└── ShopApplication.java # Spring Boot entry point
```

---

## ▶️ Running the Application

### 1. Requirements
- Java 17+
- Maven 3.8+

### 2. Build & Run
```bash
mvn clean install
mvn spring-boot:run
```

Application will start on:  
👉 `http://localhost:8080`

---

## 📌 API Endpoints

### ➕ Add Item to Cart
**POST** `/shop/{cartId}/items`  
Parameters:
- `itemName` (String)
- `price` (BigDecimal)
- `quantity` (int)

Example:
```bash
curl -X POST "http://localhost:8080/shop/cart123/items?itemName=Book&price=100&quantity=2"
```

Response:
```json
{
  "cartId": "cart123",
  "total": 200,
  "message": "Item added successfully"
}
```

---

### 💰 Get Cart Total
**GET** `/shop/{cartId}/total`  

Example:
```bash
curl -X GET "http://localhost:8080/shop/cart123/total"
```

Response:
```json
{
  "cartId": "cart123",
  "total": 200,
  "message": "Cart total retrieved"
}
```

---
## 📂 Postman Collection

To make testing easier, a Postman collection is included:

👉 [Sanlam_ShoppingCart_PostmanCollection.json](https://github.com/faneleedison-ux/sanlam-shopping-cart-test/tree/main/docs)

You can import this collection into Postman and run predefined requests for all available endpoints.

## ✅ Notes
- This project uses **in-memory storage** (HashMap) for simplicity.  
- Security, persistence, and testing are **out of scope** as per exercise instructions.  

---

## 🏆 Acknowledgement
This implementation was created by **Fanelesibonge Mbuyazi** as part of the **Sanlam FinTech Software Engineer I technical test**.  
