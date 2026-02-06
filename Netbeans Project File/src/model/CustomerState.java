package model;

public interface CustomerState {
    double getDiscount();
    void updateStatus(Customer customer);
    String getState();
}
