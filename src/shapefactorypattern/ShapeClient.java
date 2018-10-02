package shapefactorypattern;

public class ShapeClient {
    public static void main(String[] args) {
        Shape shape = ShapeFactory.createShape(ShapeType.CIRCLE);
        shape.draw();
    }
}
