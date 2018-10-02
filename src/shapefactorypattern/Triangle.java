package shapefactorypattern;

public class Triangle extends Shape {
    public Triangle() {
        super("Triangle");
    }

    @Override
    public void draw() {
        System.out.println("drawing a triangle.");
    }
}
