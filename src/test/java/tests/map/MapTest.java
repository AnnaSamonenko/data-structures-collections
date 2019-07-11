package tests.map;

import as.map.MyHashMap;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MapTest {

    private Map<Integer, String> map = new MyHashMap<>();

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
    public void testKeySet() {
        map.put(1, "lala");
        map.put(2, "434");
        map.put(17, "kee");
        map.put(1, "kee");

        Set expected = new HashSet<>();
        expected.add(1);
        expected.add(2);
        expected.add(17);


        Assert.assertEquals(expected, map.keySet());
    }

    @Test
    public void testContainsValue() {
        map.put(1, "lala");
        map.put(2, "434");
        map.put(17, "kee");
        map.put(1, "kee");


        Assert.assertTrue(map.containsValue("lala"));
        Assert.assertTrue(map.containsValue("434"));
        Assert.assertTrue(map.containsValue("kee"));
        Assert.assertFalse(map.containsValue("notPresent"));
    }

    @Test
    public void testRemoveByKey() {
        map.put(1, "olha");
        map.put(2, "anna");
        map.put(17, "565");

        HashSet<Integer> expected = new HashSet<>();
        expected.add(2);
        expected.add(1);

        Assert.assertEquals("565", map.remove(17));
        Assert.assertNull(map.get(17));
        Assert.assertEquals(expected, map.keySet());
    }

    @Test
    public void testEntrySet() {
        map.put(1, "olha");
        map.put(2, "anna");

        System.out.print(map.entrySet());
    }

}
