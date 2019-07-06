package tests.map;

import as.map.MyHashMap;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

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

}
