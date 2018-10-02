package shapefactorypattern;

public enum ShapeType {
    CIRCLE("Circle", 0),
    TRIANGLE("Triangle", 1),
    SQUARE("Square", 2);

    private final String type;
    private final int id;

    private ShapeType(String type, int id) {
        this.type = type;
        this.id = id;
    }

    public String toString() {
        return type;
    }
}
