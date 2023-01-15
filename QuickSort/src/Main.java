import Sort.*;
import Utils.*;

public class Main
{
    public static void main (String[] args)

    {
        Integer[] array = new Integer[2000000];
        Utils.Generator.GeneratorBase<Integer> generator = new Utils.Generator.IntGenerator (0, 0, 10000000);
        Utils.Array.fill(array, generator);
        Comparators.ComparatorBase<Integer> cmp = new Comparators.IntComparator();

        Sorter.SorterBase<Integer> java_sorter = new Sorter.JavaSorter<> ();
        Integer[] target_array = Utils.sort_array_and_test("Java Sort", cmp, array, java_sorter, null);

        //check insertion sort and median
        for (int i = 3; i <= 7; ++i)
        {
            for (int j = 0; j <= 1; ++j)
            {
                Boolean use_median = j == 0;
                Sorter.SorterBase<Integer> sorter = new Sorter.quickSorter<>(0, true, i, use_median);
                String title = String.format("My QuickSort with insertion (%d)", i);
                if (use_median)
                    title += " and median";
                Utils.sort_array_and_test(title, cmp, array, sorter, target_array);
            }
        }

        for (int j = 0; j <= 1; ++j)
        {
            Boolean use_median = j == 0;
            Sorter.SorterBase<Integer> sorter = new Sorter.quickSorter<>(0, false, 0, use_median);
            String title = "My QuickSort without insertion";
            if (use_median)
                title += " and median";
            Utils.sort_array_and_test(title, cmp, array, sorter, target_array);
        }

        Tests.test();
    }
}
