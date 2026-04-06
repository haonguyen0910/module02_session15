package quanlysanpham;

import java.util.*;

public class Main {
    private static List<Product> productList = new ArrayList<>();
    private static Map<String, Order> orderMap = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);
    private static int nextOrderId = 1000;

    public static void main(String[] args) {

        int choice;
        do {
            displayMenu();
            choice = getIntInput("Lựa chọn của bạn: ");

            try {
                switch (choice) {
                    case 1:
                        addProduct();
                        break;
                    case 2:
                        deleteProduct();
                        break;
                    case 3:
                        displayProducts();
                        break;
                    case 4:
                        createOrder();
                        break;
                    case 5:
                        addProductToOrder();
                        break;
                    case 6:
                        displayOrders();
                        break;
                    case 0:
                        System.out.println("Cảm ơn bạn đã sử dụng hệ thống!");
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ! Vui lòng chọn lại.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (choice != 0);

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n=========== MENU ============");
        System.out.println("1. Thêm sản phẩm");
        System.out.println("2. Xóa sản phẩm");
        System.out.println("3. Hiển thị sản phẩm");
        System.out.println("4. Tạo đơn hàng");
        System.out.println("5. Thêm sản phẩm vào đơn hàng");
        System.out.println("6. Hiển thị đơn hàng");
        System.out.println("0. Thoát");
    }

    private static int getIntInput(String input) {
        System.out.print(input);
        while (!scanner.hasNextInt()) {
            System.out.print("Vui lòng nhập số: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static double getDoubleInput(String input) {
        System.out.print(input);
        while (!scanner.hasNextDouble()) {
            System.out.print("Vui lòng nhập số: ");
            scanner.next();
        }
        return scanner.nextDouble();
    }

    private static void addProduct() throws InvalidPriceException {
        System.out.println("\n--- THÊM SẢN PHẨM ---");

        int id = getIntInput("Nhập ID sản phẩm: ");

        for (Product p : productList) {
            if (p.getId() == id) {
                System.out.println("Sản phẩm với ID " + id + " đã tồn tại!");
                return;
            }
        }

        scanner.nextLine();
        System.out.print("Nhập tên sản phẩm: ");
        String name = scanner.nextLine();

        double price = getDoubleInput("Nhập giá sản phẩm: ");

        if (price <= 0) {
            throw new InvalidPriceException("Giá sản phẩm phải lớn hơn 0!");
        }

        Product product = new Product(id, name, price);
        productList.add(product);
        System.out.println("Đã thêm sản phẩm thành công!");
    }

    private static void deleteProduct() throws ProductNotFoundException {
        System.out.println("\n--- XÓA SẢN PHẨM ---");
        int id = getIntInput("Nhập ID sản phẩm cần xóa: ");

        boolean found = false;
        Iterator<Product> iterator = productList.iterator();
        while (iterator.hasNext()) {
            Product p = iterator.next();
            if (p.getId() == id) {
                iterator.remove();
                found = true;
                System.out.println("Đã xóa sản phẩm ID " + id);
                break;
            }
        }

        if (!found) {
            throw new ProductNotFoundException("Không tìm thấy sản phẩm với ID: " + id);
        }
    }

    private static void displayProducts() {
        System.out.println("\n--- DANH SÁCH SẢN PHẨM ---");
        if (productList.isEmpty()) {
            System.out.println("Chưa có sản phẩm nào!");
        } else {
            for (Product p : productList) {
                System.out.println(p);
            }
        }
    }

    // 4. Create Order
    private static void createOrder() {
        System.out.println("\n--- TẠO ĐƠN HÀNG ---");
        String orderCode = "ORD" + nextOrderId++;

        Order order = new Order(nextOrderId - 1);
        orderMap.put(orderCode, order);

        System.out.println("Đã tạo đơn hàng với mã: " + orderCode);
    }

    private static void addProductToOrder() throws OrderNotFoundException, ProductNotFoundException, InvalidPriceException {
        System.out.println("\n--- THÊM SẢN PHẨM VÀO ĐƠN HÀNG ---");
        scanner.nextLine();
        System.out.print("Nhập mã đơn hàng (VD: ORD1000): ");
        String orderCode = scanner.nextLine();

        if (!orderMap.containsKey(orderCode)) {
            throw new OrderNotFoundException("Không tìm thấy đơn hàng với mã: " + orderCode);
        }

        displayProducts();

        if (productList.isEmpty()) {
            throw new ProductNotFoundException("Không có sản phẩm nào để thêm vào đơn hàng!");
        }

        int productId = getIntInput("Nhập ID sản phẩm cần thêm: ");

        Product selectedProduct = null;
        for (Product p : productList) {
            if (p.getId() == productId) {
                selectedProduct = p;
                break;
            }
        }

        if (selectedProduct == null) {
            throw new ProductNotFoundException("Không tìm thấy sản phẩm với ID: " + productId);
        }

        Order order = orderMap.get(orderCode);
        order.addProduct(selectedProduct);

        System.out.println("Đã thêm sản phẩm '" + selectedProduct.getName() + "' vào đơn hàng " + orderCode);
        System.out.println("Tổng tiền hiện tại của đơn hàng: " + String.format("%.2f", order.getTotalAmount()));
    }

    private static void displayOrders() {
        System.out.println("\n--- DANH SÁCH ĐƠN HÀNG ---");
        if (orderMap.isEmpty()) {
            System.out.println("Chưa có đơn hàng nào!");
        } else {
            for (Map.Entry<String, Order> entry : orderMap.entrySet()) {
                System.out.println(entry.getValue());
            }
        }
    }
}