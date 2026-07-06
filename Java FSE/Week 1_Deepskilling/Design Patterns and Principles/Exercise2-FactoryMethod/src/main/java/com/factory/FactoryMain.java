package com.factory;

/**
 * Exercise 2: Factory Method Pattern — Main Demo
 */
public class FactoryMain {

    public static void main(String[] args) {
        System.out.println("=== Factory Method Pattern Demo ===\n");

        Shape circle = ShapeFactory.getShape("CIRCLE", 5.0);
        circle.draw();
        System.out.printf("Area: %.2f%n%n", circle.calculateArea());

        Shape rectangle = ShapeFactory.getShape("RECTANGLE", 4.0, 6.0);
        rectangle.draw();
        System.out.printf("Area: %.2f%n%n", rectangle.calculateArea());

        Shape triangle = ShapeFactory.getShape("TRIANGLE", 3.0, 8.0);
        triangle.draw();
        System.out.printf("Area: %.2f%n%n", triangle.calculateArea());

        // Demonstrate error handling
        try {
            ShapeFactory.getShape("HEXAGON", 5.0);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected error: " + e.getMessage());
        }
    }
}
