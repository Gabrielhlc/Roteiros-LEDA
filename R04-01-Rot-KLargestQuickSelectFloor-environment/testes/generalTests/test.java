import orderStatistic.QuickSelect;
import org.junit.Assert;

import org.junit.Test;

import java.util.Arrays;

public class test<T extends Comparable<T>>{

    Integer[] array = {15, 38, 54, 12, 8, 10, 85};
    int k = 3;


    @Test
    public void teste1() {

        QuickSelect sorteamento = new QuickSelect<T>();

        sorteamento.quickSelect(array, k);
        Integer[] copy = {};
        copy = Arrays.copyOf(array, array.length);
        Arrays.sort(copy);
        Assert.assertArrayEquals(copy, array);
    }
}
