package shapefactorypattern;

public class Square extends Shape {
    public Square() {
        super("Square");
    }

    @Override
    public void draw() {
        System.out.println("drawing a square.");
    }
}
