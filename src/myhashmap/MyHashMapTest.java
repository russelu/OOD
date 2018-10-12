package myhashmap;

public class MyHashMapTest {
    public static void main(String[] args) {
        MyHashMap<KeyTest, String> map = new MyHashMap<>();
        KeyTest k0 = new KeyTest(0);
        KeyTest k1 = new KeyTest(1);
        KeyTest k2 = new KeyTest(101);
        KeyTest kn = null;
        System.out.println(map.get(k1));
        map.put(k0, "value0");
        System.out.println(map.get(k0));
        map.remove(k0);
        System.out.println(map.get(k0));


        map.put(k1, "value1");
        map.put(k2, "value101");
        System.out.println(map.get(k1));
        System.out.println(map.get(k2));
        // map.print();
    }
}
