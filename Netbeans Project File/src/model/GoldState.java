package model;

public class GoldState implements CustomerState {

    @Override
    public double getDiscount() {
       
        return 10.0;
    }

    @Override
    public void updateStatus(Customer customer) {
       
        if (customer.getPoints() < 1000) {
            customer.setStatus(new SilverState());
        }
    }
    @Override
    public String getState() 
    {
        return "Gold";
    }
}
