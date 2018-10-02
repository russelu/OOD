package shapefactorypattern;

public class ShapeFactory {
    public static Shape createShape(ShapeType shapeType) {
        switch (shapeType) {
            case CIRCLE:
                return new Circle();
            case SQUARE:
                return new Square();
            case TRIANGLE:
                return new Triangle();
            default:
                throw new IllegalArgumentException("No such shape type");
        }
    }
}
