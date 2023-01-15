package Sort;
import java.util.Random;
import java.util.Arrays;

public class Sorter {

    public static abstract class SorterBase<T>
    {
        private boolean m_ignore_reset = false; //for insertion sort in quick sort
        public void ignoreReset()
        {
            m_ignore_reset = true;
        }

        public long sort(T[] array, Comparators.ComparatorBase<T> comparator)
        {
            return sort(array, 0, array.length - 1, comparator);
        }

        public long sort(T[] array, int left, int right, Comparators.ComparatorBase<T> comparator)
        {
            if (!m_ignore_reset)
                comparator.reset();

            long start_time = System.currentTimeMillis();
            sortPrivate(array, left, right, comparator);
            return System.currentTimeMillis() - start_time;
        }

        public abstract void sortPrivate(T[] array, int left, int right, Comparators.ComparatorBase<T> comparator);
    }

    public static class JavaSorter<T> extends SorterBase<T>
    {
        @Override
        public void sortPrivate(T[] array, int left, int right, Comparators.ComparatorBase<T> comparator)
        {
            Arrays.sort (array, left, right + 1, comparator);
        }
    }

    public static class InsertionSorter<T> extends SorterBase<T>
    {
        @Override
        public void sortPrivate(T[] array, int left, int right, Comparators.ComparatorBase<T> comparator)
        {
            for (int i = left + 1; i <= right; ++i)
            {
                T value = array[i];
                int j = i - 1;
                while (j >= left && comparator.compare(array[j], value) > 0)
                {
                    array[j + 1] = array[j];
                    j--;
                }
                array[j + 1] = value;
            }
        }
    }

    public static class quickSorter<T> extends SorterBase<T>
    {
        private Random m_rnd;
        private boolean m_use_insertion;
        private boolean m_use_median_of_3;
        private int m_min_count_for_insertion_sort;
        private InsertionSorter<T> m_isorter = null;

        public quickSorter(int seed, boolean use_insertion, int min_count_for_insertion_sort, boolean use_median_of_3)
        {
            m_rnd = new Random (seed);
            m_use_insertion = use_insertion;
            m_use_median_of_3 = use_median_of_3;
            m_min_count_for_insertion_sort = min_count_for_insertion_sort;

            if (use_insertion)
            {
                m_isorter = new InsertionSorter<> ();
                m_isorter.ignoreReset();
            }
        }

        @Override
        public void sortPrivate(T[] array, int left, int right, Comparators.ComparatorBase<T> comparator)
        {
            while (right > left)
            {
                if (m_use_insertion && right - left < m_min_count_for_insertion_sort)
                {
                    m_isorter.sort(array, left, right, comparator);
                    return;
                }
                else
                {
                    int i = left;
                    int lt = left;
                    int gt = right;

                    T pivot;
                    if (m_use_median_of_3)
                        pivot = TukeysNinther(array, left, right, comparator);
                    else
                        pivot = array[left];

                    while (i <= gt) {
                        int cmpRes = comparator.compare(array[i], pivot);
                        if (cmpRes < 0)
                            swap(array, lt++, i++);
                        else if (cmpRes > 0)
                            swap(array, i, gt--);
                        else
                            i++;
                    }

                    if (lt - left < right - gt)
                    {
                        sortPrivate(array, left, lt - 1, comparator);
                        left = gt + 1;
                    }
                    else
                    {
                        sortPrivate(array, gt + 1, right, comparator);
                        right = lt - 1;
                    }
                }
            }
        }

        private T medianOf3(T first, T second, T third, Comparators.ComparatorBase<T> comparator)
        {
            int cmp1_2 = comparator.compare(first, second);
            int cmp1_3 = comparator.compare(first, third);
            int cmp2_3 = comparator.compare(second, third);

            if ((cmp1_2 > 0) != (cmp1_3 > 0))
                return first;
            else if ((cmp1_2 < 0) != (cmp2_3 > 0))
                return second;
            else
                return third;
        }

        private T medianOf3(T[] array, int min, int max, Comparators.ComparatorBase<T> comparator)
        {
            if (max == min + 1)
                return array[min];

            T first  = array[m_rnd.nextInt(max + 1 - min) + min];
            T second = array[m_rnd.nextInt(max + 1 - min) + min];
            T third  = array[m_rnd.nextInt(max + 1 - min) + min];

            return medianOf3(first, second, third, comparator);
        }

        private T TukeysNinther(T[] a, int min, int max, Comparators.ComparatorBase<T> comparator)
        {
            T pivot1 = medianOf3(a, min, max, comparator);
            T pivot2 = medianOf3(a, min, max, comparator);
            T pivot3 = medianOf3(a, min, max, comparator);

            return medianOf3(pivot1, pivot2, pivot3, comparator);
        }

        private static <T> void swap(T[] a, int i, int j)
        {
            T t = a[i];
            a[i] = a[j];
            a[j] = t;
        }
    }
}
