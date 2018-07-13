package test_list;

import my_list.MyArrayList;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;

public class MyArrayListTest {

    @Test
    public void testAdd() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        System.out.print(list.remove(new Integer(3)));
        for (int i = 0; i < list.size(); i++)
            System.out.print(list.get(i));
    }

    @Test
    public void testRemove() {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(9);
        list.add(10);
        list.add(11);

        Iterator itr = list.iterator();
        while (itr.hasNext())
            System.out.print(itr.next());

    }
}

