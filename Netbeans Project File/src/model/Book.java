package model;

public class Book {
    private String name;
    private double price;
    private boolean selected; 

    public Book(String name, double price) {
        this.name = name;
        this.price = price;
        this.selected = false;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }

    // For the checkbox
    public boolean isSelected() { return selected; }
    public void setSelected(boolean selected) { this.selected = selected; }
}
