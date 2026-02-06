package model;

public class Owner extends User {

    public Owner(String username, String password) {
        super(username, password);
    }
    
    public void addCustomer(Customer customer, Bookstore store) {
        store.getCustomers().add(customer);
    }
    
    public void deleteCustomer(Customer customer, Bookstore store) {
        store.getCustomers().remove(customer);
    }
    
    public void addBook(Book book, Bookstore store) {
        store.getBooks().add(book);
    }
    
    public void deleteBook(Book book, Bookstore store) {
        store.getBooks().remove(book);
    }
}
