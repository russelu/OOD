package shapefactorypattern;

public abstract class Shape {
    protected final String shapeName;

    public Shape(String shapeName) {
        this.shapeName = shapeName;
    }

    public abstract void draw();
}
