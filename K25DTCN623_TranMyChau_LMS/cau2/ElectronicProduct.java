package cau2;

public class ElectronicProduct extends Product {
    private int warrantyMonths;
    private double discount;

    // === Contructor
    public ElectronicProduct(){}

    public ElectronicProduct(String proID, String proName, double price, int warrantyMonths, double discount){
        // Dùng super để kế thừa ở product
        super(proID, proName, price);
        this.warrantyMonths = warrantyMonths;
        this.discount = discount;
    }

    // === Getter/Setter
    // 1. Getter
    public int getWarrantyMonth(){return warrantyMonths;}
    public double getDiscount(){return discount;}

    // 2. Setter
    public void setWarrantyMonth(int warrantyMonths){this.warrantyMonths = warrantyMonths;}
    public void setDiscount(double discount){this.discount = discount;}

    // === Phương thức thêm
    public double getSalePrice(){
        return getPrice() - this.discount;
    }

    // === Các hàm chuẩn bị in
    @Override public String toString() {
    return String.format("| %-10s | %-25s | %-10.2f | %-15s | %-10.2f | %-10.2f |", getProID(), getProName(), getPrice(), warrantyMonths + " thang", discount, getSalePrice());
    }

    public void showInfo(){
        System.out.println(this.toString());
    }
}
