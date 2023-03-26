package reflection;

public abstract class Car implements Vehicle {
	public static final String TYPE = "Car";

    private String make;

    public Car(String make) {
        this.make = make;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    protected abstract int getMileage();
    // Constructors, standard getters and setters omitted
}
