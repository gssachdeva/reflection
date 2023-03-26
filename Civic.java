package reflection;

public class Civic extends Car {
	private int mileage;
	
	private int price;

    public Civic() {
        super("Honda Civic");
        setMileage(15);
        price = 10000;
    }
    
    public Civic(String make) {
        super(make);
        setMileage(15);
        price = 10000;
    }
    
    public Civic(String make, int mileage) {
        super(make);
        setMileage(mileage);
        price = 10000;
    }

    @Override
    public String getVehicleType() {
        return "Car";
    }

    @Override
    protected int getMileage() {
    	return mileage;
    }

    public int getPrice() {
        return price;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }
}
