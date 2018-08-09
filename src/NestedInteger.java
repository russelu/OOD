import java.util.*;

public class NestedInteger implements Nesteable {
    private Integer val;
    private List<Nesteable> list;

    public NestedInteger() {
        val = null;
        list = new LinkedList<Nesteable>();
    }

    public NestedInteger(int value) {
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
    public void add(Nesteable ni) {
        if (val != null) throw new IllegalStateException("Object is an Integer.");
        list.add(ni);
    }

    @Override
    public List<Nesteable> getList() {
        if (val != null) throw new IllegalStateException("Object is an Integer.");
        return list;
    }
}

interface Nesteable {
    boolean isInteger();
    Integer getInteger();
    void setInteger(int value);
    void add(Nesteable ni);
    List<Nesteable> getList();
}