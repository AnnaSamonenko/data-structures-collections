package tests.map;

import as.map.MyHashMap;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MapTest {

    Map<Integer, String> map = new MyHashMap();

    @Test
    public void testPut() {
        map.put(1, "olha");
        map.put(2, "anna");

        Assert.assertEquals("olha", map.get(1));
        Assert.assertEquals("anna", map.get(2));
    }

    @Test
    public void testCollision() {
        map.put(17, "collision");

        Assert.assertEquals("collision", map.get(17));
    }

    @Test
    public void testPutByRepeatedKey() {
        map.put(18, "old");
        map.put(18, "new");

        Assert.assertEquals("new", map.get(18));
    }

    @Test
    public void testContainsKey() {
        map.put(4, "new");

        Assert.assertTrue(map.containsKey(4));
        Assert.assertFalse(map.containsKey(20));
    }

    @Test
    public void testGetByUnexistedKey() {
        Assert.assertNull(map.get(20));
    }

    @Test
    public void testClear() {
        map.put(3, "new");
        map.clear();

        Assert.assertNull(map.get(3));
        Assert.assertEquals(0, map.size());
    }

    @Test
    public void testKeySet(){
        map.put(1, "lala");
        map.put(2, "434");
        map.put(17, "kee");
        map.put(1, "kee");

        int[] arr = {1, 2, 17, 1};

        Set expected = new HashSet<>();
        expected.add(1);
        expected.add(2);
        expected.add(17);


        Assert.assertEquals(expected, map.keySet());
    }

}
