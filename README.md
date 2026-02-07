# Bookstore Point of Sale (POS) Application
A Java-based desktop application designed to manage bookstore operations, featuring distinct workflows for administrators (owners) and customers. The project demonstrates proficiency in object-oriented design, persistent storage, and interactive UI development.

## 🛠 Tech Stack
Language: Java

Framework: JavaFX (GUI) with FXML for view management

Persistence: File-based I/O (TXT)

Architecture: Model-View-Controller (MVC)

## Key Features
Dual-Role Authentication: Secure login for "Owner" and "Customer" roles.

Inventory Management: Real-time CRUD operations for book listings and customer profiles.

Loyalty Points System: A dynamic reward system where customers earn points based on purchases.

Persistent Data: Automatic loading and saving of books and customer data to local text files upon application startup and closure.

## Technical Highlights
State Design Pattern: Implements the State pattern (CustomerState) to manage customer tiers (Silver/Gold) and dynamic discount logic based on loyalty points.

Object-Oriented Design: Utilizes abstract classes (User) and inheritance to manage common authentication logic across different user types.

File I/O Management: Robust data handling using BufferedReader and PrintWriter to maintain a local database without external dependencies.

## How to Run
Clone the repository.

Open as a NetBeans project.

Run Main.java: The application initializes by loading data from books.txt and customers.txt.

Default Admin Credentials:

Username: admin

Password: admin
