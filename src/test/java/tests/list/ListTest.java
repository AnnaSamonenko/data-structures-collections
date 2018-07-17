package tests.list;

import as.list.MyArrayList;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.*;

public class ListTest {

    private List<String> list = new MyArrayList<>();
    //private List<String> list = new MyLinkedList<>();

    @BeforeMethod
    public void before() {
        for (int i = 0; i < 3; i++)
            list.add("" + i);
        compare(list, new String[]{"0", "1", "2"});
    }

    @Test
    public void testToArray() {
        Object[] arrayList = list.toArray();
        Assert.assertTrue(Arrays.deepEquals(arrayList, new Object[]{"0", "1", "2"}));
    }

    //TODO: add test method to the add with param

    @Test
    public void testInsertByIndex() {
        Assert.assertEquals(list.size(), 3);
        list.add(1, "Insert");
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
        list.remove("2");
        compare(list, new String[]{"0", "1"});
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
        Assert.assertEquals(list.size(), 3);
        compare(list, new String[]{"0", "1", "4"});
    }

    @Test
    public void testContainsAll() {
        List arrayList = new MyArrayList();
        arrayList.add("0");
        arrayList.add("2");
        arrayList.add("1");

        Assert.assertTrue(list.containsAll(arrayList));
        list.remove("1");
        Assert.assertFalse(list.containsAll(arrayList));
    }

    @Test
    public void testAddAllToTheEnd() {
        List arrayList = new MyArrayList();
        arrayList.add("5");
        arrayList.add("6");
        arrayList.add("7");

        list.addAll(arrayList);
        compare(list, new String[]{"0", "1", "2", "5", "6", "7"});
    }

    @Test
    public void testAddAllByIndex() {
        List arrayList = new MyArrayList();
        arrayList.add("5");
        arrayList.add("6");
        arrayList.add("7");

        list.addAll(1, arrayList);
        compare(list, new String[]{"0", "1", "5", "6", "7", "2"});
    }

    @Test
    public void testRemoveAll() {
        List arrayList = new MyArrayList();
        arrayList.add("5");
        arrayList.add("1");
        arrayList.add("0");

        list.removeAll(arrayList);
        compare(list, new String[]{"2"});
    }

    @Test
    public void testRetainAll() {
        List arrayList = new LinkedList();
        arrayList.add("2");
        arrayList.add("0");
        list.retainAll(arrayList);
        compare(list, new String[]{"0", "2"});
    }

    @Test
    public void testIteratorHasPrevious() {
        ListIterator itr = list.listIterator(0);
        Assert.assertFalse(itr.hasPrevious());
        itr.next();
        Assert.assertTrue(itr.hasPrevious());
    }

    @Test
    public void testIteratorIndex() {
        ListIterator itr = list.listIterator(2);
        Assert.assertEquals(itr.nextIndex(), 3);
        Assert.assertEquals(itr.previousIndex(), 1);
    }

    @Test
    public void testIterator() {
        ListIterator itr = list.listIterator(2);
        Assert.assertEquals(itr.previous(), "1");
    }

    @Test
    public void testIteratorRemove() {
        list.add("10");
        list.add("16");
        Iterator itr = list.listIterator(2);
        itr.remove();
        compare(list, new String[]{"0", "1", "10", "16"});
    }

    @Test
    public void testIteratorSet() {
        ListIterator itr = list.listIterator(1);
        itr.set("10");
        compare(list, new String[]{"0", "10", "2"});
    }

    @Test
    public void testIteratorAdd() {
        ListIterator itr = list.listIterator(1);
        itr.add("10");
        compare(list, new String[]{"0", "1", "10", "2"});
    }

    @AfterMethod
    public void after() {
        list.clear();
    }

    private void compare(List lst, String[] strs) {
        Object[] array = lst.toArray();
        Assert.assertEquals(array.length, strs.length, "Arrays not the same length");
        for (int i = 0; i < array.length; i++)
            Assert.assertEquals(strs[i], (String) array[i]);
    }

    private void print(List x) {
        for (int i = 0; i < x.size(); i++) {
            System.out.print(x.get(i) + " ");
        }
    }
}
