package reflection;

public class Ferrari extends Car implements Racing {
	private int mileage;
	
	public Ferrari(String make) {
		super(make);
		mileage = 5;
	}
	
	@Override
    protected int getMileage() {
    	return mileage;
    }
	
    @Override
    public String getRaceCategory() {
        return "F1";
    }

    @Override
    public String getVehicleType() {
        return "Car";
    }
}
