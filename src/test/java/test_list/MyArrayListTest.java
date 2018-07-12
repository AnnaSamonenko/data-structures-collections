package test_list;

import my_list.MyArrayList;
import org.junit.Test;

public class MyArrayListTest {

    @Test
    public void test() {
//        Object[] a = new Object[10];
//        for (int i = 0; i < 10; i++)
//            a[i] = 1;
//        a[5] = null;
//        for(int i = 0; i < a.length; i++){
//            System.out.print(a[i]);
//        }

        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.remove(3);
        for (int i = 0; i < list.size(); i++)
            System.out.print(list.get(i));
    }
}

