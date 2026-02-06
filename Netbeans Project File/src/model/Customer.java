package model;

import java.util.ArrayList;

public class Customer extends User {
    private int points;
    private CustomerState status;
    private ArrayList<Book> cart = new ArrayList<>();

    public Customer(String username, String password) {
        super(username, password);
        this.points = 0;
        
        this.status = new SilverState();
    }

    public void addBook(Book book) {
        cart.add(book);
    }

    public void removeBook(Book book) {
        cart.remove(book);
    }

    public void buyBooks(double amount) {
        
        this.points += (int)(amount * 10);
        updateStatus();
    }

    public double redeemPoints(double amount) {
       
        int redeemablePoints = Math.min(points, (int)(amount * 100));
        double discount = redeemablePoints / 100.0;
        this.points -= redeemablePoints;
        updateStatus();
        double finalPrice = amount - discount;
        return finalPrice < 0 ? 0 : finalPrice;
    }

    public void updateStatus() {
        status.updateStatus(this);
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public CustomerState getStatus() {
        return status;
    }

    public void setStatus(CustomerState status) {
        this.status = status;
    }

 
    public String getUsername() {
        return super.getUsername();
    }

    public String getPassword() {
        return super.getPassword();
    }
}
