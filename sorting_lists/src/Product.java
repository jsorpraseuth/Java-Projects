public class Product {

    private int ID;
    private String name;
    private double price;

    // constructors
    public Product() {
        this.ID = 0;
        this.name = "Unknown";
        this.price = 0;
    }
    public Product(int ID, String name, double price) {
        this.ID = ID;
        this.name = name;
        this.price = price;
    }

    public int getID() {
        return this.ID;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

}
