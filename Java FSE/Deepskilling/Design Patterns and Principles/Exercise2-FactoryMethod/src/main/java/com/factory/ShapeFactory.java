package com.factory;

/**
 * Factory class — decouples object creation from client code.
 * Client never uses 'new' directly for shapes.
 */
public class ShapeFactory {

    public static Shape getShape(String shapeType, double... dimensions) {
        if (shapeType == null || shapeType.isEmpty()) {
            throw new IllegalArgumentException("Shape type cannot be null or empty.");
        }
        switch (shapeType.toUpperCase()) {
            case "CIRCLE":
                if (dimensions.length < 1) throw new IllegalArgumentException("Circle requires 1 dimension (radius).");
                return new Circle(dimensions[0]);
            case "RECTANGLE":
                if (dimensions.length < 2) throw new IllegalArgumentException("Rectangle requires 2 dimensions (width, height).");
                return new Rectangle(dimensions[0], dimensions[1]);
            case "TRIANGLE":
                if (dimensions.length < 2) throw new IllegalArgumentException("Triangle requires 2 dimensions (base, height).");
                return new Triangle(dimensions[0], dimensions[1]);
            default:
                throw new IllegalArgumentException("Unknown shape type: " + shapeType);
        }
    }
}
