# SweetsDelight - Online Sweets & Bakery Store

### Description
SweetDelight is an online Sweets and Bakery store that allows customers to browse through various Sweets & bakery products and place orders. The project is implemented using Spring Boot and provides RESTful APIs for managing products, categories, customers, carts, and orders. The application is designed to be user-friendly and secure, with different functionalities available for administrators and customers.

Deployed Netlify link:   https://enchanting-cupcake-7ed0a8.netlify.app/ <br>

## HomePage
![http://url/to/img.png](https://github.com/RADHIKESHS/frail-power-8560/blob/main/Home%20page.jpg)

## ProductPage
![http://url/to/img.png](https://github.com/RADHIKESHS/frail-power-8560/blob/main/productpage.jpg)

## AdminPage
![http://url/to/img.png](https://github.com/RADHIKESHS/frail-power-8560/blob/main/Adminpanel.jpg)

## Table of Contents
- [Installation]([Frail Power 8560 GitHub Repo](https://github.com/RADHIKESHS/frail-power-8560))
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Contributors](#contributors)

## Installation
1. Clone the repository from GitHub: [Sweet-Delight](https://github.com/RADHIKESHS/frail-power-8560)
2. Install Java JDK and Spring Boot.
3. Setup the database (MySQL, PostgreSQL, or any supported by Spring Data JPA).
4. Configure the application properties file with database credentials.
5. Build and run the application using Maven or your preferred IDE.

## Usage
### As an Admin, you can:
- Manage products and categories.
- View all users and customers.
- Manage orders and bills.

### As a Customer, you can:
- Browse products by category.
- Add products to your cart.
- Place and track orders.
- View your order history.

## Technology Stack
The Frail Power 8560 project uses the following technologies:

- Java
- Spring Boot
- Spring Data JPA
- MySQL (or any supported database by Spring Data JPA)
- React (Frontend)
- HTML, CSS, JavaScript

## Database ER Diagram
![http://url/to/img.png](https://github.com/RADHIKESHS/frail-power-8560/blob/main/Sweetdelight%20database%20ER.png?raw=true)

## API Endpoints
### Admin & Customer Endpoints
- DELETE /sweetDelight/delete/{customerid} - Delete a customer (Admin & User)
- GET /sweetDelight/products - Get all products (Admin & User)
- GET /sweetDelight/categories - Get all categories (Admin & User)
- GET /sweetDelight/categories/{id} - Get a category by ID (Admin & User)
- GET /sweetDelight/products/sorted - Get all products with sorting (Admin & User)
- GET /sweetDelight/category - Get all categories (Admin & User)
- GET /sweetDelight/searchbyname - Search products by name (Admin & User)
- GET /sweetDelight/product/getallproduct - Get all products with pagination (Admin & User)
- GET /sweetDelight/product/getallavailableproduct - Get all available products with pagination (Admin & User)

### Admin Endpoints
- POST /sweetDelight/admin/register - Register a new admin user
- GET /sweetDelight/admin/users - Get all users (Admin)
- GET /sweetDelight/admin/customers - Get all customers (Admin)
- GET /sweetDelight/admin/customers/{customerId} - Get customer details by ID (Admin)
- POST /sweetDelight/admin/products/{categoryId} - Add a product to a category (Admin)
- PUT /sweetDelight/admin/product/update/{productId} - Update product details (Admin)
- DELETE /sweetDelight/admin/products/{productId} - Delete a product (Admin)
- GET /sweetDelight/admin/products/{productId} - Get a product by ID (Admin)
- PUT /sweetDelight/admin/products/{prodId}/categories/{catId} - Add a product to a category (Admin)
- POST /sweetDelight/admin/category - Add a new category (Admin)
- PUT /sweetDelight/admin/categories/update/{categoryId} - Update category details (Admin)
- DELETE /sweetDelight/admin/categories/{categoryId} - Delete a category (Admin)
- GET /sweetDelight/admin/allcarts - Get all carts (Admin)
- GET /sweetDelight/admin/bills/allbills - Get all order bills (Admin)
- GET /sweetDelight/admin/orderbill/{customerbillid} - Get order details by ID (Admin)
- GET /sweetDelight/admin/orders/allorders - Get all orders (Admin)
- GET /sweetDelight/admin/logini - Admin login endpoint

### Customer Endpoints
- POST /sweetDelight/customerUser/register - Register a new customer
- PUT /sweetDelight/customerUser/update/{customerId} - Update customer details
- PUT /sweetDelight/customerUser/carts/{customerId}/add/{productId} - Add product to cart
- PUT /sweetDelight/customerUser/carts/{customerId}/remove/{productId} - Remove product from cart
- GET /sweetDelight/customerUser/carts/{customerId} - Get cart products by customer ID
- GET /sweetDelight/customerUser/price/{orderid} - Get total price of an order
- GET /sweetDelight/customerUser/getallorders/{customerId} - Get all orders of a customer
- GET /sweetDelight/customerUser/cart/{id}/product - Get products in the cart by customer ID
- GET /sweetDelight/customerUser/logini - Customer login endpoint

## Contributors
- Subir Adhikari: Worked on the service layer, implementing various business logic and functionalities.
- Deepak Yadav: Contributed to the service layer, handling data processing and business operations.
- Radhikesh Leader: Implemented JWT security for authentication and authorization in the application.
- Rahul K Thakur: Led the JPA entity mapping and database design, ensuring data persistence and integrity.
- Vivek Sharma: Worked on the frontend development, designing and implementing the user interface.

Special thanks to all the contributors for their hard work and dedication in making this project a success!

## Author
SweetsDelight's Team
