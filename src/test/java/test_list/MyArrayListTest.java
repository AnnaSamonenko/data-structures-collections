package test_list;

import my_list.MyArrayList;
import my_list.MyLinkedList;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;


public class MyArrayListTest {

    private List<String> list = new MyArrayList<>();
    private List<String> list1 = new MyLinkedList<>();

    @BeforeClass
    public void before() {
        for (int i = 0; i < 3; i++)
            list.add("" + i);
    }

    @Test
    public void testInsert() {
        System.out.println("Running testInsert()");
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
        System.out.println("Running testOrder()");
        compare(list, new String[]{"0", "1", "2"});
    }

    @Test
    public void testRemove() {
        System.out.println("Running testRemove()");
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

