package tests.map;

import as.map.MyHashMap;
import org.testng.annotations.Test;

import java.util.Map;

public class MapTest {

    Map<Integer, String> map = new MyHashMap();

    @Test
    public void testPut() {
        map.put(1, "olha");
        map.put(2, "anna");

        System.out.println(map.get(1));
        System.out.println(map.get(2));
    }

    @Test
    public void testCollision() {
        map.put(17, "collision");

        System.out.println(map.get(17));
    }

    @Test
    public void testPutByRepeatedKey() {
        map.put(18, "old");
        map.put(18, "new");

        System.out.println(map.get(18));
    }


    @Test
    public void testGetByUnexistedKey() {
        System.out.println(map.get(20));
    }

}
