package com.ecommerce;

/**
 * Product entity for the e-commerce platform.
 */
public class Product {
    private int productId;
    private String productName;
    private String category;
    private double price;

    public Product(int productId, String productName, String category, double price) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.price = price;
    }

    public int getProductId()       { return productId; }
    public String getProductName()  { return productName; }
    public String getCategory()     { return category; }
    public double getPrice()        { return price; }

    @Override
    public String toString() {
        return String.format("Product{id=%d, name='%s', category='%s', price=%.2f}",
                productId, productName, category, price);
    }
}
