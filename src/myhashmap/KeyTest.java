package myhashmap;

public class KeyTest {
    private final int k;
    private Integer hashCode;
    public KeyTest(int k) {
        this.k = k;
        hashCode = null;
    }
    @Override
    public int hashCode() {
        if (hashCode != null) return hashCode;
        return hashCode = k % 50;
    }
    @Override
    public boolean equals(Object other) {
        if (other != null && other instanceof KeyTest) {
            KeyTest that = (KeyTest) other;
            return k == that.k;
        }
        return false;
    }
}
