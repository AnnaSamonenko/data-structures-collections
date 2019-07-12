package tests.map;

import as.map.MyHashMap;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MapTest {

    private Map<Integer, String> map = new MyHashMap<>();

    @AfterMethod
    public void after() {
        map.clear();
    }

    @Test
    public void testPut() {
        map.put(1, "olha");
        map.put(2, "anna");

        Assert.assertEquals(map.get(1), "olha");
        Assert.assertEquals(map.get(2), "anna");
    }

    @Test
    public void testCollision() {
        map.put(17, "collision");

        Assert.assertEquals(map.get(17), "collision");
    }

    @Test
    public void testPutByRepeatedKey() {
        map.put(18, "old");
        map.put(18, "new");

        Assert.assertEquals(map.get(18), "new");
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

        Assert.assertEquals(map.remove(17), "565");
        Assert.assertNull(map.get(17));
        Assert.assertEquals(map.keySet(), expected);
    }

    @Test
    public void testEntrySet() {
        map.put(1, "olha");
        map.put(2, "anna");

        System.out.print(map.entrySet());
    }

}
