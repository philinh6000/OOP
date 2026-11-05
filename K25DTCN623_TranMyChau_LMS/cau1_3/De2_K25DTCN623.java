package cau1_3;
import java.util.*;
import utils.Utils;
import cau2.ElectronicProduct;
import cau2.Store;

public class De2_K25DTCN623 {
    // === Câu 1 ===
    // 1a. Nhập mảng n số nguyên
    // 1a.1 Nhập n
    public static int nhapN(Scanner sc){ // Dùng kiểu Scanner cho sc để nhập từ bàn phím
        int n;
        // Dùng do-while để bắt nhập lại khi n < 1 hoặc > 100
        do{
            Utils.Msg("Nhap n (1<=n<=100): ");
            n = sc.nextInt();
        } while (n < 1 || n > 100);
        return n;
    }
    // 1a.2 Nhập mảng
    public static int[] nhapMang(Scanner sc){ 
        // Gọi hàm nhập n
        int n = nhapN(sc);
        // Tạo mảng có kích thước n
        int[] A = new int[n];
        // Nhập vào mảng
        Utils.Msg("Nhap cac phan tu cua mang A: ");
        for (int i = 0; i < n; i++) A[i] = sc.nextInt();
        // Trả về mảng A
        return A;
    }

    // 1b. Tìm ra những số chẵn chỉ xuất hiện 1 lần và trả về các số chẵn đó.
    // Hàm kiểm tra số chẵn
    public static boolean isEven(int n){
        return n%2 == 0; // nếu chia hết cho 2 -> true
    }

    // Không yêu cầu các số trả về đúng thứ tự -> dùng hash map.
    public static Map<Integer, Integer> addMap(int[] A){
        // Dùng Hash map để đếm số lần xuất hiện của các số chẵn
        Map<Integer, Integer> mapDem = new HashMap<>();

        // Lặp qua các số trong A, nếu x là số chẵn thì đưa vào map
        // map.put(key, value): thêm số x vào key, số lần xuất hiện vào value
        // m = map.getOrDefault(x, 0) -> lấy ra số lần xuất hiện hiện tại của x, nếu x chưa từng có trong map thì tạm lấy giá trị 0.
        // map.put(x, m+1) -> khi xuất hiện x, mỗi lần xuất hiện +1 => 1 lần = 1.
        for (int x : A) if (isEven(x)) mapDem.put(x, mapDem.getOrDefault(x, 0) + 1);
        return mapDem;
    }

    // Lấy số có value == 1 trong map
    public static List<Integer> onlyOne(Map<Integer, Integer> mapDem){
        // Tạo mảng chứa động (vì chưa biết số lượng số n)
        List<Integer> tempList = new ArrayList<>();
        // Duyệt trực tiếp trong map trên các key (các số x đã là số chẵn sẵn) và chỉ lấy khi value == 1 -> thêm vào tempList
        for (int x : mapDem.keySet()) if (mapDem.get(x) == 1) tempList.add(x); // Nếu lấy số x trong map kiểm tra value == 1 -> thêm số x (key) đó của map vào tempList.
        return tempList;
    }

    // Chuyển đổi kiểu từ List sang int[] để đúng kiểu trả về
    public static int[] chuyenKieuMang(List<Integer> tempList){
        int[] result = new int[tempList.size()]; // mảng result có số phần tử = số size của tempList
        for (int i = 0; i < tempList.size(); i++) result[i] = tempList.get(i);

        return result;
    }

    // Controller
    public static int[] evensNonDup(int[] A){
        // 1. Lấy số chẵn vào map và đếm số phần tử
        Map<Integer, Integer> mapDem = addMap(A);

        // 2. Duyệt map và lấy ra tempList các key có value == 1
        List<Integer> tempList = onlyOne(mapDem);

        // 3. Chuyển List về lại int[] để trả về theo kiểu mảng ban đầu.
        int[] result = chuyenKieuMang(tempList);

        return result;
    }

    // === CÂU 3
    // a. Trong lớp MaDeThi_MaSV, định nghĩa phương thức:
    public static void testClasses(){
        // 1. Tạo một đối tượng Store
        Store str = new Store("PTIT Store");

        // 2. Tạo và thêm ít nhất 4 sản phẩm vào Store (tự đặt ví dụ để tạo - đề không yêu cầu nhập từ bàn phím)
        str.addProduct(new ElectronicProduct("E01", "Laptop Dell", 1500, 12, 100));
        str.addProduct(new ElectronicProduct("E02", "iPhone 15", 1200, 24, 50));
        str.addProduct(new ElectronicProduct("E03", "Headphone Sony", 200, 6, 20));
        str.addProduct(new ElectronicProduct("E04", "Mouse Logitech", 50, 12, 5));

        // 3. Hiển thị danh sách sản phẩm
        Utils.Msg("Danh sach ban dau:");
        str.showAll(); // Dùng showAll để in header và các sp bên trong

        // 4. Sắp xếp theo giá bán và hiển thị kết quả.
        Utils.Msg("Danh sach sau khi sort:");
        str.sortByPrice(); // Sắp xếp
        str.showAll(); // In lại

        // 5. Xóa một sản phẩm theo mã và hiển thị lại danh sách - giả sử xóa mã E03
        String keyword = "E03";
        ElectronicProduct removed = str.removeProduct(keyword); // Xóa
        // Kiểm tra xóa thất bại thì báo lại
        if (removed == null) Utils.Msg("Khong tim thay "+ keyword);
        else {
            Utils.Msg("Danh sach sau khi xoa:");
            str.showAll(); // In lại
        }

        // 6. Tìm kiếm sản phẩm theo mã hoặc tên và hiển thị kết quả. - giả sử keyword là 'I'
        keyword = "I";
        List<ElectronicProduct> result = str.searchProduct(keyword); // Tìm
        // Nếu result rỗng -> không có kết quả nào
        if (result.isEmpty()) Utils.Msg("Khong tim thay "+ keyword);
        else{
            // In ra danh sach gần giống
            Utils.Msg("Ket qua tim kiem cua "+ keyword);
            str.printHeader(); // In ra header theo định nghĩa ở store
            // In ra các phần tử trong result
            for(ElectronicProduct x : result) x.showInfo();
        }
    }

    // b. gọi testClasses() trong phương thức main().
    public static void main(String[] args){
        testClasses();
    }
}
