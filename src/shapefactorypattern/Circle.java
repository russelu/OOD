package shapefactorypattern;

public class Circle extends Shape {
    public Circle() {
        super("Circle");
    }

    @Override
    public void draw() {
        System.out.println("drawing a circle.");
    }
}
