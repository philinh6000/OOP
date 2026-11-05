package cau2;

public abstract class Product {// class ElectronicProduct kế thừa Product -> Product là class cha (abstract)
    private String productID;
    private String productName;
    private double price;

    // === Khởi tạo Constructor
    public Product(){}

    public Product(String proID, String proName, double gia){
        this.productID = proID;
        this.productName = proName;
        this.price = gia;
    }

    // Getter/Setter
    // 1. Getter: lấy thông tin
    public String getProID(){return productID;}
    public String getProName(){return productName;}
    public double getPrice(){return price;}

    // 2. Setter: Đặt thông tin vào private
    public void setProID(String proID){this.productID = proID;}
    public void setProName(String proName){this.productName = proName;}
    public void setPrice(double gia){this.price = gia;}
}
