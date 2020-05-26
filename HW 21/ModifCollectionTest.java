import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class ModifCollectionTest {
    private ModifCollection<Integer> collection;

    @Before
    public void setUp() {
        collection = new ModifCollection<>();
    }

    @Test
    public void testConstrIsNotEmpty() {
        ArrayList<Integer> arrayList = new ArrayList<>(Arrays.asList(0, 1));
        collection = new ModifCollection<>(arrayList);
        Iterator<Integer> it = collection.iterator();
        int a = it.next();
        int b = it.next();
        Assert.assertTrue(((a == 1 && b == 0) || (a == 0 && b == 1)) && collection.size() == 2);
    }

    @Test
    public void testFirstAdd() {
        collection.add(30);
        Iterator<Integer> it = collection.iterator();
        int a = it.next();
        Assert.assertEquals(a, 30);
    }

    @Test(expected = NullPointerException.class)
    public void testSecondAdd() {
        collection.add(null);
    }

    @Test
    public void testThirdAdd() {
        collection.add(0);
        collection.add(0);
        collection.add(1);
        collection.add(0);
        Assert.assertEquals(collection.size(), 4);
    }

    @Test
    public void testFirstSize() {
        int a = collection.size();
        collection.add(0);
        collection.add(1);
        int b = collection.size();
        collection.add(3);
        collection.add(0);
        int c = collection.size();
        Assert.assertTrue(a == 0 && b == 2 && c == 4);
    }

    @Test
    public void testRemove() {
        collection.add(0);
        collection.add(100);
        collection.add(20);
        Iterator<Integer> it = collection.iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
        Assert.assertEquals(collection.size(), 0);
    }

    @Test
    public void testRemoveForOneElement() {
        collection.add(0);
        collection.add(100);
        collection.add(200);
        collection.add(200);
        Iterator<Integer> it = collection.iterator();
        while (it.hasNext()) {
            int a = it.next();
            if (a == 200) {
                it.remove();
            }
        }
        Assert.assertEquals(collection.size(), 2);
    }

    @Test
    public void testFirstIteratorHasNext() {
        collection.add(0);
        collection.add(1);
        Iterator<Integer> it = collection.iterator();
        Assert.assertTrue(it.hasNext());
    }

    @Test
    public void testSecondIteratorHasNext() {
        Iterator<Integer> it = collection.iterator();
        Assert.assertFalse(it.hasNext());
    }

    @Test
    public void testContainsElem() {
        collection.add(3);
        collection.add(100);
        collection.add(43);
        collection.add(55);
        collection.add(45);
        Iterator<Integer> it = collection.iterator();
        boolean flag = false;
        int a = 55;
        while (it.hasNext()) {
            if (it.next() == 55) {
                flag = true;
                break;
            }
        }
        Assert.assertTrue(flag);
    }

    @Test
    public void testIsEmpty() {
        Assert.assertEquals(collection.size(), 0);
    }

    @Test
    public void testFirstEquals() {
        collection.add(1);
        collection.add(5);
        ModifCollection<Integer> second = new ModifCollection<>();
        second.add(1);
        second.add(5);
        Assert.assertEquals(collection, second);
    }

    @Test
    public void testSecondEquals() {
        collection.add(1);
        collection.add(3);
        collection.add(5);
        ModifCollection<Integer> second = new ModifCollection<>();
        second.add(5);
        second.add(3);
        second.add(1);
        Assert.assertEquals(collection, second);
    }

    @Test
    public void testHashCode() {
        collection.add(1);
        ModifCollection<Integer> second = new ModifCollection<>();
        second.add(1);
        Assert.assertEquals(collection.hashCode(), second.hashCode());
    }

    @Test(expected = NullPointerException.class)
    public void testConstrWithNull() {
        collection = new ModifCollection<>(null);
    }
}