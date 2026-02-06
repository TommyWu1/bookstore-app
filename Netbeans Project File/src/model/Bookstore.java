package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Bookstore {
    private List<Book> books;
    private List<Customer> customers;

    public Bookstore() {
        books = new ArrayList<>();
        customers = new ArrayList<>();
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void saveData() {
        saveBooks();
        saveCustomers();
    }

    private void saveBooks() {
        try (PrintWriter writer = new PrintWriter("books.txt")) {
            for (Book b : books) {
                
                writer.println(b.getName() + "," + b.getPrice());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveCustomers() {
        try (PrintWriter writer = new PrintWriter("customers.txt")) {
            for (Customer c : customers) {
                
                writer.println(c.getUsername() + "," + c.getPassword() + "," + c.getPoints());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   
    public void loadData() {
        loadBooks();
        loadCustomers();
    }

    private void loadBooks() {
        books.clear();
        File file = new File("books.txt");
        if (!file.exists()) {
            System.out.println("No books.txt found. Skipping load.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String name = parts[0];
                    double price = Double.parseDouble(parts[1]);
                    books.add(new Book(name, price));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadCustomers() {
        customers.clear();
        File file = new File("customers.txt");
        if (!file.exists()) {
            System.out.println("No customers.txt found. Skipping load.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
               
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String username = parts[0];
                    String password = parts[1];
                    int points = Integer.parseInt(parts[2]);

                    Customer c = new Customer(username, password);
                    c.setPoints(points);
                    c.updateStatus(); 
                    customers.add(c);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    public Customer findCustomer(String username, String password) {
        for (Customer c : customers) {
            if (c.logIn(username, password)) {
                return c;
            }
        }
        return null;
    }
}
