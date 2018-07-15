package tests.list;

import as.list.MyArrayList;
import as.list.MyLinkedList;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ListTest {

    private List<String> list = new MyArrayList<>();
    private List<String> list1 = new MyLinkedList<>();

    @BeforeMethod
    public void before() {
        for (int i = 0; i < 3; i++)
            list.add("" + i);
        compare(list, new String[]{"0", "1", "2"});
    }

    @Test
    public void testInsertByIndex() {
        Assert.assertEquals(list.size(), 3);
        list.add(1, "Insert");
        print();
        Assert.assertEquals(list.size(), 4);
        Assert.assertEquals(list.get(2), "Insert");
    }

    @Test
    public void testInsertToTheEnd() {
        Assert.assertEquals(list.size(), 3);
        list.add("Insert2");
        Assert.assertEquals(list.size(), 4);
        Assert.assertEquals(list.get(list.size() - 1), "Insert2");
    }

    @Test
    public void testSet() {
        Assert.assertEquals(list.size(), 3);
        list.set(1, "Set");
        print();
        Assert.assertEquals(list.size(), 3);
        Assert.assertEquals(list.get(1), "Set");
    }

    @Test
    public void testClear() {
        list.clear();
        Assert.assertEquals(list.size(), 0);
    }

    @Test
    public void testContainsOf() {
        Assert.assertTrue(list.contains("1"));
        Assert.assertFalse(list.contains("6"));
    }

    @Test
    public void testRemoveByValue() {
        list.remove("0");
        print();
        compare(list, new String[]{"1", "2"});
    }

    @Test
    public void testIndexOf() {
        Assert.assertEquals(list.indexOf("1"), 1);
        Assert.assertEquals(list.indexOf("6"), -1);
    }

    @Test
    public void testOrder() {
        compare(list, new String[]{"0", "1", "2"});
    }

    @Test
    public void testRemoveByIndex() {
        Assert.assertEquals(list.size(), 3);
        list.add("4");
        list.remove(2);
        print();
        Assert.assertEquals(list.size(), 2);
        compare(list, new String[]{"0", "1"});
    }

    private void compare(List lst, String[] strs) {
        Object[] array = lst.toArray();
        Assert.assertTrue(array.length == strs.length, "Arrays not the same length");
        for (int i = 0; i < array.length; i++)
            Assert.assertEquals(strs[i], (String) array[i]);
    }

    private void print() {
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
    }

    @AfterMethod
    public void after() {
        list.clear();
    }

}

