import java.util.*;

public class NestedIntegerClass implements NestedInteger {
    private Integer val;
    private List<NestedInteger> list;

    public NestedIntegerClass() {
        val = null;
        list = new LinkedList<NestedInteger>();
    }

    public NestedIntegerClass(int value) {
        // if (list != null) throw new IllegalStateException("Object is already an Integer.");
        val = value;
        list = null;
    }

    @Override
    public boolean isInteger() {
        return val != null;
    }

    @Override
    public Integer getInteger() {
        return val;
    }

    @Override
    public void setInteger(int value) {
        if (list != null) throw new IllegalStateException("Object is a list.");
        val = value;
    }

    @Override
    public void add(NestedInteger ni) {
        if (val != null) throw new IllegalStateException("Object is an Integer.");
        list.add(ni);
    }

    @Override
    public List<NestedInteger> getList() {
        if (val != null) throw new IllegalStateException("Object is an Integer.");
        return list;
    }
}

interface NestedInteger {
    boolean isInteger();
    Integer getInteger();
    void setInteger(int value);
    void add(NestedInteger ni);
    List<NestedInteger> getList();
}