package Utils;

import Sort.Comparators;
import Sort.Sorter;

import java.util.Arrays;
import java.util.Random;

import static Utils.Utils.Array.*;

public class Utils
{
    public class Array
    {
        public static <T> void fill(T[] array, Generator.GeneratorBase<T> generator)
        {
            fill(array, 0, array.length - 1, generator);
        }

        private static <T> void fill(T[] array, int left, int right, Generator.GeneratorBase<T> generator)
        {
            for (int i = left; i < array.length && i <= right; i++)
            {
                array[i] = generator.nextValue();
            }
        }

        private static int MaxPrint = 10;

        public static <T> void print(T[] array)
        {
            if (array.length > MaxPrint)
            {
                int shift = MaxPrint / 2;
                print(array, 0, shift);
                System.out.print(" ... ");
                print(array, array.length - 1 - shift, array.length);
                System.out.println();
            }
            else
            {
                print(array, 0, array.length);
                System.out.println();
            }

        }

        private static <T> void print(T[] array, int left, int right)
        {
            int i = left;
            for (; i < array.length && i < right; i++)
            {
                System.out.print(array[i] + " ");
            }
            if (i < array.length)
                System.out.print(array[right]);
        }
    }

    public class Generator
    {
        public static abstract class GeneratorBase<T>
        {
            protected Random m_random;
            protected T m_min, m_max;
            public abstract T nextValue();
        }

        public static class IntGenerator extends GeneratorBase<Integer>
        {
            public IntGenerator (int seed, int min, int max)
            {
                m_random = new Random(seed);
                m_min = min;
                m_max = max;
            }

            @Override
            public Integer nextValue()
            {
                return m_random.nextInt(m_max - m_min) + m_min;
            }
        }

        public static class DoubleGenerator extends GeneratorBase<Double> {
            public DoubleGenerator(int seed, double min, double max) {
                m_random = new Random(seed);
                m_min = min;
                m_max = max;
            }

            @Override
            public Double nextValue() {
                return m_random.nextDouble(m_max - m_min) + m_min;
            }
        }
    }

    public static <T> T[] sort_array_and_test  (String title, Comparators.ComparatorBase<T> comparator, T[] array, Sorter.SorterBase<T> sorter, T[] target_array)
    {
        System.out.println(title);

        T[] array_copy = array.clone();
        System.out.print("Before sort: ");
        print(array_copy);
        long time_elapsed = sorter.sort(array_copy, comparator);
        System.out.print("After sort:  ");
        print(array_copy);

        System.out.println("Cmp Count: " + comparator.getCount());
        System.out.println("Elapsed time: " + time_elapsed / 1000.);

        if (target_array != null)
        {
            boolean equal = Arrays.equals (array_copy, target_array);
            System.out.println(equal ? "Is ok" : "Is not ok");
        }

        System.out.println();

        return array_copy;
    }
}
