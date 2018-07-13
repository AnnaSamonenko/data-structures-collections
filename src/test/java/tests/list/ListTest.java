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
    }

    @AfterMethod
    public void after() {
        list.clear();
    }

    @Test
    public void testInsert() {
        Assert.assertEquals(list.size(), 3);
        list.add(1, "Insert");
        Assert.assertEquals(list.size(), 4);
        Assert.assertEquals(list.get(1), "Insert");
    }

    @Test
    public void testReplace() {
        System.out.println("Running testReplace()");
        Assert.assertEquals(list.size(), 3);
        list.set(1, "Replace");
        Assert.assertEquals(list.size(), 3);
        Assert.assertEquals(list.get(1), "Replace");
    }

    @Test
    public void testOrder() {
        compare(list, new String[]{"0", "1", "2"});
    }

    @Test
    public void testRemove() {
        Assert.assertEquals(list.size(), 3);
        list.remove(1);
        Assert.assertEquals(list.size(), 2);
        compare(list, new String[]{"0", "2"});
    }

    private void compare(List lst, String[] strs) {
        Object[] array = lst.toArray();
        Assert.assertTrue(array.length == strs.length, "Arrays not the same length");
        for (int i = 0; i < array.length; i++)
            Assert.assertEquals(strs[i], (String) array[i]);
    }

}

