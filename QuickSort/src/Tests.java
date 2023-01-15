import Sort.*;

import java.util.Arrays;

import Utils.Utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Tests
{

    public static void test()
    {
        testSortDoubles();
        testSortStrings();
    }

    @Test
    @DisplayName("Sort Doubles")
    public static void testSortDoubles()
    {
        Utils.Generator.GeneratorBase<Double> generator = new Utils.Generator.DoubleGenerator (1, 0, 10000);

        Double[] array = new Double[100000];
        Utils.Array.fill(array, generator);

        Comparators.ComparatorBase<Double> comparator = new Comparators.DoubleComparator();

        Double[] copy_java = array.clone();
        Arrays.sort(copy_java, comparator);

        Sorter.SorterBase<Double> qsort = new Sorter.quickSorter<>(0,false, 0, false);
        Double[] copy_qsort = array.clone();
        qsort.sort(copy_qsort, comparator);

        assertArrayEquals(copy_java, copy_qsort);
    }
    @Test
    @DisplayName("Sort Strings")
    public static void testSortStrings()
    {
        String[] strings = {"aaa", "cba", "abc", "bab", "cab", "bca", "a", "b", "c"};
        Comparators.ComparatorBase<String> comparator = new Comparators.StringComparator();

        String[] copy_java = strings.clone();
        Arrays.sort(copy_java, comparator);

        Sorter.SorterBase<String> qsort = new Sorter.quickSorter<>(0, false, 0, true);
        String[] copy_qsort = strings.clone();
        qsort.sort(copy_qsort, comparator);

        assertArrayEquals(copy_java, copy_qsort);
    }

}