package quanlysanpham;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderId;
    private List<Product> products;

    public Order(int orderId) {
        this.orderId = orderId;
        this.products = new ArrayList<>();
    }

    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public double getTotalAmount() {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("========== ORDER #").append(orderId).append(" ==========\n");
        if (products.isEmpty()) {
            sb.append("No products in this order.\n");
        } else {
            for (Product product : products) {
                sb.append(product).append("\n");
            }
            sb.append("Total Amount: ").append(String.format("%.2f", getTotalAmount())).append("\n");
        }
        return sb.toString();
    }
}