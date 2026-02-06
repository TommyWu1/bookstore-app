package model;

public class SilverState implements CustomerState {

    @Override
    public double getDiscount() {
       
        return 5.0;
    }

    @Override
    public void updateStatus(Customer customer) {
     
        if (customer.getPoints() >= 1000) {
            customer.setStatus(new GoldState());
        }
    }
    @Override
     public String getState() 
    {
        return "Silver";
    }
     
}
