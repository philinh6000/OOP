package cau2;

import java.util.*;
import utils.Utils;

public class Store {
    private String storeName;
    private List<ElectronicProduct> products = new ArrayList<>();; // Khởi tạo list để truyền

    // Constructor: 
    public Store(){}

    public Store(String storeName){this.storeName = storeName;}

    // Getter
    public String getStoreName(){return storeName;}
    public List<ElectronicProduct> getList(){return products;} // Lấy list ra để dùng

    // Setter đưa new list vào list products
    public void setList(List<ElectronicProduct> newList){this.products = newList;}

    // === Phương thức
    // b. Thêm một sản phẩm vào danh sách. (0.5 điểm)
    public void addProduct(ElectronicProduct product){
        if (product != null) products.add(product);
    }

    // c. Xóa sản phẩm có mã productId khỏi danh sách và trả về đối tượng vừa bị xóa. (1 điểm)
    public ElectronicProduct removeProduct(String proID){
        // Duyệt trong list để tìm id, , nếu có thì xóa và trả về i, nếu không có trả về null
        for (int i = 0; i < products.size(); i++) if (products.get(i).getProID().equalsIgnoreCase(proID)) {
            return products.remove(i); // Xóa đối tượng i trong danh sách products khỏi list và trả i về
        }
        return null;
    }
    // d. Sắp xếp danh sách sản phẩm theo giá bán (getSalePrice()) tăng dần. (0.5 điểm)
    public void sortByPrice(){
        // Dùng Collection và Comparator để sort bằng hàm thư viện
        Collections.sort(products, new Comparator<ElectronicProduct>() {
            @Override public int compare(ElectronicProduct s1, ElectronicProduct s2){
                // Giá sắp xếp tăng dần và ép kiểu float khi so sánh
                return Double.compare(s1.getSalePrice(), s2.getSalePrice());
            }
        });
    }
    // e. Tìm kiếm sản phẩm theo mã hoặc tên. (0.5 điểm) -> tìm theo keyword gần giống
    public List<ElectronicProduct> searchProduct(String keyword){
        // Tạo list result
        List<ElectronicProduct> result = new ArrayList<>();
        // Đặt keyword về viết thường
        keyword = keyword.toLowerCase();
        // Lặp trong danh sách products, dùng contains để lấy gần giống
        for (ElectronicProduct x : products) if(x.getProID().toLowerCase().contains(keyword) || x.getProName().toLowerCase().contains(keyword)) result.add(x); // Nếu có mã hoặc tên gần giống keyword thì thêm vào list result
        // Kết thúc vòng lặp -> trả về danh sách kết quả gần giống.
        return result;
    }

    // Các hàm in
    // Kiểm tra list rỗng
    public boolean isEmptyList(String errorMsg){
        if (products.isEmpty()){
            Utils.Msg(errorMsg);
            return true;
        }
        return false;
    }

    // In bảng tiêu đề
    public void printHeader(){
        System.out.printf("| %-10s | %-25s | %-10s | %-15s | %-10s | %-10s |\n", "ID", "Product Name", "Price", "Warranty Month", "Discount", "Sale Price");
    }

    // in tất cả trong list
    public void showAll(){
        // Kiểm tra rỗng trước
        if (isEmptyList("Products List empty!!")) return;
        printHeader();
        for (ElectronicProduct x : products) x.showInfo();
    }
}
