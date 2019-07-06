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
        map.put(3, "polina");

        System.out.println(map.get(1));
        System.out.println(map.get(2));
        System.out.println(map.get(3));
    }

}
