package tests.map;

import org.testng.annotations.Test;

import java.util.ArrayList;

public class MapTest {

    @Test
    public void testToArray() {
        ArrayList ll = new ArrayList();
        ll.add("anna");
        ll.add("masha");
        ll.add("olga");
        ll.add("sasha");
        String[] names = new String[3];
        ll.toArray(names);

    }

}
