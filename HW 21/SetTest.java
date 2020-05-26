import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class SetTest {
    private Set<Integer> set;

    @Before
    public void setUp() {
        Integer[] data = new Integer[]{1, 2, 3, 3};
        set = new Set<>(data);
    }

    @Test
    public void testConstrWithData() {
        Integer[] data = new Integer[]{1, 2, 3, 4};
        set = new Set<>(data);
        Assert.assertEquals(set.size(), 4);
    }

    @Test
    public void testAdd() {
        set.add(0);
        Assert.assertEquals(set.size(), 4);
    }

    @Test
    public void testSizeAfterAddDuplicates() {
        set.add(3);
        set.add(2);
        set.add(1);
        Assert.assertEquals(set.size(), 3);
    }

    @Test
    public void testAddDublicate() {
        set.add(2);
        Assert.assertEquals(set.size(), 3);
    }

    @Test
    public void testIsHas() {
        set.add(6);
        Assert.assertTrue(set.isHas(6));
    }

    @Test
    public void testDelete() {
        set.delete(3);
        set.delete(1);
        set.delete(2);
        Assert.assertEquals(set.size(), 0);
    }

    @Test
    public void testDelete2() {
        set.delete(2);
        Assert.assertEquals(set.size(), 2);
    }

    @Test
    public void testDeleteAddIsHas() {
        set.delete(3);
        set.add(3);
        Assert.assertTrue(set.isHas(3));
    }

     @Test
    public void testDeleteAddSize() {
        set.delete(3);
        set.add(3);
        Assert.assertEquals(set.size(), 3);
    }

    @Test
    public void testDeleteIsHas() {
        set.delete(1);
        Assert.assertFalse(set.isHas(1));
    }

    @Test
    public void testMergeWithDifferent() {
        Integer[] data = new Integer[]{1, 2, 3, 4};
        Set<Integer> second = new Set<>(data);
        int[] s = {1, 2, 3, 4};
        Assert.assertEquals(Arrays.toString(s), set.merge(second).toString());
    }

    @Test
    public void testMergeSame() {
        Integer[] data = new Integer[]{1, 2, 3, 3};
        Set<Integer> second = new Set<>(data);
        Integer[] s = {1, 2, 3, null};
        Assert.assertEquals(Arrays.toString(s), set.merge(second).toString());
    }

    @Test
    public void testEqualsSameTrue() {
        Integer[] data = new Integer[]{1, 2, 3};
        Set<Integer> second = new Set<>(data);
        Assert.assertEquals(set, second);
    }

    @Test
    public void testEqualsDiffTrue() {
        Integer[] data = new Integer[]{2, 1, 3};
        Set<Integer> second = new Set<>(data);
        Assert.assertEquals(set, second);
    }

    @Test
    public void testEqualsFalse() {
        Integer[] data = new Integer[]{1, 2, 3, 4};
        Set<Integer> second = new Set<>(data);
        Assert.assertNotEquals(set, second);
    }

    @Test
    public void testHashCode() {
        Integer[] data = new Integer[]{1, 2, 3, 3};
        Set<Integer> second = new Set<>(data);
        Assert.assertEquals(set.hashCode(), second.hashCode());
    }
}