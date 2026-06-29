package com.ecommerce;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Exercise 2: E-commerce Platform Search Function
 *
 * Demonstrates:
 * - Linear Search: O(n) time, O(1) space — works on unsorted data
 * - Binary Search: O(log n) time, O(1) space — requires sorted data
 *
 * Big-O Analysis:
 * Linear  → O(n): checks each element until match found or end
 * Binary  → O(log n): eliminates half the search space each iteration
 */
public class ProductSearch {

    /**
     * Linear Search by product name — O(n)
     * Suitable for small catalogs or unsorted data.
     */
    public static Product linearSearchByName(Product[] products, String targetName) {
        for (int i = 0; i < products.length; i++) {
            if (products[i].getProductName().equalsIgnoreCase(targetName)) {
                System.out.println("Linear search found at index: " + i);
                return products[i];
            }
        }
        return null;
    }

    /**
     * Binary Search by product ID — O(log n)
     * Requires array sorted ascending by productId.
     */
    public static Product binarySearchById(Product[] products, int targetId) {
        int left = 0, right = products.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (products[mid].getProductId() == targetId) {
                System.out.println("Binary search found at index: " + mid);
                return products[mid];
            } else if (products[mid].getProductId() < targetId) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Product[] products = {
            new Product(1, "Laptop",        "Electronics", 1500.00),
            new Product(2, "Running Shoes", "Footwear",      80.00),
            new Product(3, "Java Book",     "Books",          45.00),
            new Product(4, "Smartphone",    "Electronics",   999.00),
            new Product(5, "Yoga Mat",      "Fitness",        30.00),
            new Product(6, "Headphones",    "Electronics",   250.00),
            new Product(7, "Backpack",      "Accessories",    60.00)
        };

        // Sort by ID for binary search (already sorted here, but showing the step)
        Arrays.sort(products, Comparator.comparingInt(Product::getProductId));

        System.out.println("=== E-commerce Search Demo ===\n");

        // Linear Search
        System.out.println("--- Linear Search (by name: 'Smartphone') ---");
        long start = System.nanoTime();
        Product result = linearSearchByName(products, "Smartphone");
        long duration = System.nanoTime() - start;
        System.out.println("Result: " + (result != null ? result : "Not found"));
        System.out.println("Time: " + duration + " ns\n");

        // Binary Search
        System.out.println("--- Binary Search (by ID: 3) ---");
        start = System.nanoTime();
        result = binarySearchById(products, 3);
        duration = System.nanoTime() - start;
        System.out.println("Result: " + (result != null ? result : "Not found"));
        System.out.println("Time: " + duration + " ns\n");

        // Not found case
        System.out.println("--- Linear Search (by name: 'Tablet') ---");
        result = linearSearchByName(products, "Tablet");
        System.out.println("Result: " + (result != null ? result : "Product not found in catalog."));

        System.out.println("\nBig-O Summary:");
        System.out.println("Linear Search: O(n) — suitable for small/unsorted catalogs");
        System.out.println("Binary Search: O(log n) — preferred for large sorted catalogs");
    }
}
