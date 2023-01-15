package Sort;

import java.util.Comparator;

public class Comparators
{
    public static abstract class ComparatorBase<T> implements Comparator<T>
    {
        private int m_count = 0;
        public int getCount()  { return m_count; }
        public void reset() { m_count = 0;}

        @Override
        public int compare(T v1, T v2)
        {
            m_count++;
            return comparePrivate(v1, v2);
        }
        protected abstract int comparePrivate(T v1, T v2);
    }

    public static class IntComparator extends ComparatorBase<Integer> {
        @Override
        protected int comparePrivate(Integer v1, Integer v2) { return v1 - v2; }
    }

    public static class DoubleComparator extends ComparatorBase<Double> {
        @Override
        protected int comparePrivate(Double v1, Double v2)
        {
            return Double.compare(v1, v2);
        }
    }

    public static class StringComparator extends ComparatorBase<String> {
        @Override
        protected int comparePrivate(String s1, String s2)
        {
            return s1.compareTo(s2);
        }
    }
}


